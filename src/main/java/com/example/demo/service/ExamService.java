package com.example.demo.service;

import com.example.demo.requestModel.ExamRequestModel;
import com.example.demo.requestModel.QuestionsRequestModel;
import com.example.demo.responseModel.ResponseModel;

import java.util.List;

public interface ExamService {

    /**
     * method to prepare & return a new ExamRequestModel instance
     *
     * @param examRequestModel
     * @return
     */
    ResponseModel<?> saveExam(ExamRequestModel examRequestModel);
}
