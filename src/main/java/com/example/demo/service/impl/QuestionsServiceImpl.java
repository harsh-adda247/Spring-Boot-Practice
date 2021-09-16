package com.example.demo.service.impl;

import com.example.demo.entities.QuestionEntity;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.requestModel.QuestionsRequestModel;
import com.example.demo.responseModel.QuestionResponseModel;
import com.example.demo.responseModel.ResponseModel;
import com.example.demo.service.QuestionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class QuestionsServiceImpl implements QuestionsService {

    @Autowired
    private QuestionRepository questionRepository;

    private List<String> list = new ArrayList<>();

    private static Logger logger = LoggerFactory.getLogger(QuestionsServiceImpl.class);

    @Override
    public ResponseModel<QuestionResponseModel> saveQuestion(QuestionsRequestModel questionsRequestModel) {
        logger.info("**** Inside QuestionServiceImpl ==> saveQuestion ****");
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuestion(questionsRequestModel.getQuestion());
        list = generateOptions(questionsRequestModel);
        questionEntity.setOption1(list.get(0));
        questionEntity.setOption2(list.get(1));
        questionEntity.setOption3(list.get(2));
        questionEntity.setOption4(list.get(3));
        questionEntity.setExamId(questionsRequestModel.getExamId());
        questionEntity.setAnswer(questionsRequestModel.getAnswer());

        questionEntity = questionRepository.save(questionEntity);
        logger.info("question saved successfully");
        QuestionResponseModel response = prepareQuestionResponse(questionEntity.getQuestion(), questionEntity.getAnswer(), list);
        return new ResponseModel<QuestionResponseModel>(HttpStatus.OK, "Question saved successfully", null, response);
    }

    private List<String> generateOptions(QuestionsRequestModel questionsRequestModel) {
        Set<Map.Entry<String, Boolean>> entrySet = questionsRequestModel.getOptions().entrySet();
        for (Map.Entry<String, Boolean> map : entrySet) {
            list.add(map.getKey());
        }
        return list;
    }

    private QuestionResponseModel prepareQuestionResponse(String question, String answer, List<String> options) {
        QuestionResponseModel questionResponseModel = new QuestionResponseModel();
        questionResponseModel.setQuestionl(question);
        questionResponseModel.setOptions(options);
        questionResponseModel.setAnswer(answer);
        return questionResponseModel;
    }
}
