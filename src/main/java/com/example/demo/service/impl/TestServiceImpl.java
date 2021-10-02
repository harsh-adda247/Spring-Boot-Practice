package com.example.demo.service.impl;

import com.example.demo.entities.TestEntity;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.requestModel.TestRequestModel;
import com.example.demo.responseModel.ResponseModel;
import com.example.demo.responseModel.TestResponseModel;
import com.example.demo.service.TestService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private QuestionRepository questionRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public TestServiceImpl() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public ResponseModel<TestResponseModel> saveTest(List<TestRequestModel> testRequestModel,
                                                     Integer rollNo, String branch) {
        if(!validateRollNoAndBranch(rollNo, branch)){
            return new ResponseModel<>(HttpStatus.BAD_REQUEST, "Roll No or branch of student missing"
                    , null, null);
        }
        TestEntity testEntity = new TestEntity();
        testEntity.setRollNo(rollNo);
        testEntity.setBranch(branch);
        testEntity.setMarks(computeMarksForStudent(testRequestModel));
        return null;
    }

    /**
     * method to validate the rollNo and branch of the student provided
     * @param rollNo
     * @param branch
     * @return
     */
    private static boolean validateRollNoAndBranch(Integer rollNo, String branch){
        if(rollNo == null || StringUtils.isEmpty(branch)) return false;
        return true;
    }


    private static int computeMarksForStudent(List<TestRequestModel> testRequestModels){
        Set<Integer> questionIds = generateQuestionsIds(testRequestModels.size()-1, testRequestModels);
        return 0;
    }

    private static Set<Integer> generateQuestionsIds(int index, List<TestRequestModel> testRequestModels){
        //Base Case
        if(index < 0) return new HashSet<Integer>();
        //faith
        Set<Integer> questionsIds = generateQuestionsIds(index - 1, testRequestModels);
        //faith * expectations
        questionsIds.add(testRequestModels.get(index).getQuestionId());
        return  questionsIds;
    }
}
