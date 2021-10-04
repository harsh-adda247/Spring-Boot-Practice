package com.example.demo.controller;

import com.example.demo.requestModel.TestRequestModel;
import com.example.demo.responseModel.ResponseModel;
import com.example.demo.responseModel.TestResponseModel;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("/attempt")
    public ResponseEntity<?> attemptExam(@Valid @RequestBody TestRequestModel testRequestModel){
        ResponseModel<TestResponseModel> responseModel = testService.saveTest(testRequestModel);
        return new ResponseEntity<>(responseModel.getResponse(), HttpStatus.valueOf(responseModel.getStatus()));
    }
}
