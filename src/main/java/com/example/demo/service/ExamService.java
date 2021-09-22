package com.example.demo.service;

import com.example.demo.requestModel.ExamRequestModel;
import com.example.demo.responseModel.ResponseModel;

public interface ExamService {

    /**
     * method to prepare & return a new ExamRequestModel instance
     *
     * @param examRequestModel
     * @return
     */
    ResponseModel<?> saveExam(ExamRequestModel examRequestModel);

    /**
     * method to delete exam corresponding to the provided examId
     *
     * @param examId
     * @return
     */
    ResponseModel<?> deleteExam(Integer examId);
}
