package com.example.demo.service.impl;

import com.example.demo.entities.QuestionEntity;
import com.example.demo.entities.TestEntity;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.TestRepository;
import com.example.demo.requestModel.QuestionAnswerRequestModel;
import com.example.demo.requestModel.TestRequestModel;
import com.example.demo.responseModel.ResponseModel;
import com.example.demo.responseModel.TestResponseModel;
import com.example.demo.service.TestService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TestRepository testRepository;

    private Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    private ModelMapper modelMapper = new ModelMapper();

    public TestServiceImpl() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public ResponseModel<TestResponseModel> saveTest(TestRequestModel testRequestModel) {
        if (!validateRollNoAndBranch(testRequestModel.getRollNo(), testRequestModel.getBranch())) {
            return new ResponseModel<>(HttpStatus.BAD_REQUEST, "Roll No or branch of student missing"
                    , null, null);
        }
        TestEntity testEntity = new TestEntity();
        testEntity.setExamId(testRequestModel.getExamId());
        testEntity.setRollNo(testRequestModel.getRollNo());
        testEntity.setBranch(testRequestModel.getBranch());
        Integer marks = computeMarksForStudent(testRequestModel);
        if (marks == null) {
            logger.info("Invalid examId : " + testRequestModel.getExamId());
            return new ResponseModel<>(HttpStatus.BAD_REQUEST, "Invalid examId : " + testRequestModel.getExamId(),
                    null, null);
        }
        testEntity.setMarks(marks);
        testEntity.setName(testRequestModel.getName());
        testEntity = this.testRepository.save(testEntity);
        logger.info("test data saved successfully");
        TestResponseModel response = modelMapper.map(testEntity, TestResponseModel.class);
        response.setName(testEntity.getName());
        return new ResponseModel<>(HttpStatus.OK, "Student's Marks", null, response);
    }

    /**
     * method to validate the rollNo and branch of the student provided
     *
     * @param rollNo
     * @param branch
     * @return
     */
    private boolean validateRollNoAndBranch(Integer rollNo, String branch) {
        if (rollNo == null || StringUtils.isEmpty(branch)) return false;
        return true;
    }


    private Integer computeMarksForStudent(TestRequestModel testRequestModel) {
        Map<Integer, String> questionAnswers = generateQuestionAnswers(testRequestModel.getQuestionAnswers().size() - 1,
                testRequestModel);
        List<QuestionEntity> entities = this.questionRepository.findAllOrderByExamId(testRequestModel.getExamId());
        if (entities == null) {
            logger.info("No questions found for examId : " + testRequestModel.getExamId());
            return null;
        }
        Set<Map.Entry<Integer, String>> entrySet = questionAnswers.entrySet();
        Integer counter = 0, index = 0;
        for (Map.Entry<Integer, String> map : entrySet) {
            if (map.getKey() == entities.get(index).getQuestionId()) {
                if (map.getValue().equals(entities.get(index).getAnswer())) counter += 1;
            }
            index += 1;
        }
        return counter;
    }

    private Map<Integer, String> generateQuestionAnswers(int index, TestRequestModel testRequestModel) {
        //Base Case
        if (index < 0) return new HashMap<Integer, String>();
        //faith
        Map<Integer, String> questionAnswers = generateQuestionAnswers(index - 1, testRequestModel);
        //faith * expectations
        QuestionAnswerRequestModel questionAnswer = testRequestModel.getQuestionAnswers().get(index);
        questionAnswers.put(questionAnswer.getQuestionId(), questionAnswer.getAnswer());
        return questionAnswers;
    }

}
