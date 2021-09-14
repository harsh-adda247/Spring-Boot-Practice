package com.example.demo.controller;

import com.example.demo.requestModel.ExamRequestModel;
import com.example.demo.responseModel.ResponseModel;
import com.example.demo.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping("/create")
    public ResponseEntity<ResponseModel<?>> createExam(@Valid @RequestBody ExamRequestModel examRequestModel){
        ResponseModel<?> responseModel = examService.saveExam(examRequestModel);
        return new ResponseEntity<>(responseModel, HttpStatus.valueOf(responseModel.getStatus()));
    }
}
