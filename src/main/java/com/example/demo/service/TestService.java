package com.example.demo.service;

import com.example.demo.requestModel.TestRequestModel;
import com.example.demo.responseModel.ResponseModel;
import com.example.demo.responseModel.TestResponseModel;

import java.util.List;

public interface TestService {

    /**
     * method to save user performance in a particular test
     *
     * @param testRequestModel
     * @return
     */
    ResponseModel<TestResponseModel> saveTest(TestRequestModel testRequestModel);
}
