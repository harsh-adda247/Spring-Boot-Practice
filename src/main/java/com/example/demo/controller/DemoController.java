package com.example.demo.controller;

import com.example.demo.entities.QuestionEntity;
import com.example.demo.entities.StudentEntity;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class DemoController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @ResponseBody
    @GetMapping("/demo/test/async")
    public String test() throws ExecutionException, InterruptedException {
        CompletableFuture<StudentEntity> future1 = CompletableFuture
                .supplyAsync(() -> {
                   return studentRepository.findByRollNoAndBranch(1, "CSE");
                });

        CompletableFuture<List<QuestionEntity>> future2 = CompletableFuture
                .supplyAsync(() -> {
                   return questionRepository.findAll();
                });

        System.out.println(future1.get());
        System.out.println("Size = "+future2.get().size());
        return "Success";
    }

    @GetMapping("/demo/like/test")
    public ResponseEntity<List<StudentEntity>> getRollNumbers() {
        Pageable page = PageRequest.of(0, 2);
        List<StudentEntity> list1 = studentRepository.getByRollNumbers(page,9).toList();
        return new ResponseEntity<>(list1, HttpStatus.OK);
    }
}
