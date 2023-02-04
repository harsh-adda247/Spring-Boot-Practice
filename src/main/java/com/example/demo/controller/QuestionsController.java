package com.example.demo.controller;

import com.example.demo.entities.QuestionEntity;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.requestModel.QuestionsRequestModel;
import com.example.demo.responseModel.QuestionResponseModel;
import com.example.demo.responseModel.ResponseModel;
import com.example.demo.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/question")
public class QuestionsController {

    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("/create")
    public ResponseEntity<ResponseModel<?>> createQuestion(@Valid @RequestBody QuestionsRequestModel question) {
        question = question.identifyAnswer(question);
        ResponseModel<QuestionResponseModel> questionResponse = questionsService.saveQuestion(question);
        return new ResponseEntity<>(questionResponse, HttpStatus.valueOf(questionResponse.getStatus()));
    }

    @PostMapping("/save-questions")
    public ResponseEntity<ResponseModel<List<QuestionResponseModel>>> createMultipleQuestions
            (@Valid @RequestBody List<QuestionsRequestModel> questionsRequestModels) {
        for (QuestionsRequestModel question : questionsRequestModels) {
            question = question.identifyAnswer(question);
        }
        ResponseModel<List<QuestionResponseModel>> response = questionsService.saveQuestions(questionsRequestModels);
        return new ResponseEntity<ResponseModel<List<QuestionResponseModel>>>
                (response, HttpStatus.valueOf(response.getStatus()));
    }

    @GetMapping("/search")
    public ResponseEntity<List<QuestionEntity>> getList() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("answer").descending());
        Page<QuestionEntity> entities = questionRepository.findByAnswerLike(pageable,"G%");

        List<QuestionEntity> list = entities.get().collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/search/x2")
    public ResponseEntity<List<QuestionEntity>> getListX2() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("answer").descending());
        Page<QuestionEntity> entities = questionRepository.findByAnswer(pageable,null);

        List<QuestionEntity> list = entities.get().collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
