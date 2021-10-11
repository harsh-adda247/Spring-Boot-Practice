package com.example.demo.service.impl;

import com.example.demo.entities.TestEntity;
import com.example.demo.repository.TestRepository;
import com.example.demo.requestModel.LeaderboardRequestModel;
import com.example.demo.responseModel.LeaderboardResponseModel;
import com.example.demo.responseModel.ResponseModel;
import com.example.demo.service.LeaderboardService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaderboardServiceImpl implements LeaderboardService {

    @Autowired
    private TestRepository testRepository;

    private ModelMapper modelMapper = new ModelMapper();

    private Logger logger = LoggerFactory.getLogger(LeaderboardServiceImpl.class);

    public LeaderboardServiceImpl() {
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * method to fetch leaderboard for an exam regardless of the branch
     *
     * @param leaderboardRequestModel
     * @returnd
     */
    @Override
    public ResponseModel<List<LeaderboardResponseModel>> fetchLeaderboardForExam(LeaderboardRequestModel leaderboardRequestModel) {
        logger.info("Inside LeaderboardServiceImpl ==> fetchLeaderboardForExam()");
        List<TestEntity> testEntities = testRepository.getLeaderboardForExam(leaderboardRequestModel.getExamId());
        if (testEntities == null || testEntities.size() == 0) {
            logger.info("No leaderboard found for examId : "+leaderboardRequestModel.getExamId());
            return new ResponseModel<>(HttpStatus.BAD_REQUEST, "No leaderboard found for the exam id :" + leaderboardRequestModel.getExamId(),
                    null, null);
        }
        logger.info("leaderboard found successfully");
        List<LeaderboardResponseModel> response = convertEntityIntoResponse(testEntities);
        return new ResponseModel<>(HttpStatus.OK, "Leaderboard retrieved successfully", null, response);
    }

    /**
     * method to fetch leaderboard for an exam, corresponding to a specific branch
     *
     * @param leaderboardRequestModel
     * @return
     */
    @Override
    public ResponseModel<List<LeaderboardResponseModel>> fetchLeaderboardForExamAndBranch(LeaderboardRequestModel leaderboardRequestModel) {
        logger.info("Inside LeaderboardServiceImpl ==> fetchLeaderboardForExamAndBranch()");
        if(StringUtils.isEmpty(leaderboardRequestModel.getBranch())){
            logger.info("Error : Branch is either null or empty, couldn't proceed further");
            return new ResponseModel<>(HttpStatus.BAD_REQUEST, "Branch can't be missing or empty", "Validation Error", null);
        }
        List<TestEntity> testEntities = testRepository.getLeaderboardForExamAndBranch(leaderboardRequestModel.getExamId(),
                leaderboardRequestModel.getBranch());
        if (testEntities == null || testEntities.size() == 0) {
            logger.info("No leaderboard found for examId : "+leaderboardRequestModel.getExamId());
            return new ResponseModel<>(HttpStatus.BAD_REQUEST, "No leaderboard found for the exam id :" + leaderboardRequestModel.getExamId(),
                    null, null);
        }
        logger.info("leaderboard found successfully");
        List<LeaderboardResponseModel> response = convertEntityIntoResponse(testEntities);
        return new ResponseModel<>(HttpStatus.OK, "Leaderboard retrieved successfully", null, response);
    }

    /**
     * method to map list of entities into list of response
     *
     * @param entities
     * @return
     */
    private List<LeaderboardResponseModel> convertEntityIntoResponse(List<TestEntity> entities) {
        List<LeaderboardResponseModel> responses = new ArrayList<>();
        for (TestEntity entity : entities) {
            responses.add(this.modelMapper.map(entity, LeaderboardResponseModel.class));
        }
        return responses;
    }
}
