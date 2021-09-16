package com.example.demo.controller;

import com.example.demo.requestModel.QuestionsRequestModel;
import com.example.demo.responseModel.QuestionResponseModel;
import com.example.demo.responseModel.ResponseModel;
import com.example.demo.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/question")
public class QuestionsController {

    @Autowired
    private QuestionsService questionsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseModel<?>> createQuestion(@Valid @RequestBody QuestionsRequestModel question){
       question = question.identifyAnswer(question);
       ResponseModel<QuestionResponseModel> questionResponse = questionsService.saveQuestion(question);
       return new ResponseEntity<>(questionResponse, HttpStatus.valueOf(questionResponse.getStatus()));
    }
}
