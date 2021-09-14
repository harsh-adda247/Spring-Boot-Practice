package com.example.demo.service;

import com.example.demo.requestModel.QuestionsRequestModel;
import com.example.demo.responseModel.QuestionResponseModel;
import com.example.demo.responseModel.ResponseModel;

public interface QuestionsService {

    /**
     * method to save a new question
     * @param questionsRequestModel
     * @return
     */
    ResponseModel<QuestionResponseModel> saveQuestion(QuestionsRequestModel questionsRequestModel);
}
