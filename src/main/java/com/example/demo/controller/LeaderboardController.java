package com.example.demo.controller;

import com.example.demo.requestModel.LeaderboardRequestModel;
import com.example.demo.responseModel.LeaderboardResponseModel;
import com.example.demo.responseModel.ResponseModel;
import com.example.demo.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/leaderboard/exam")
public class LeaderboardController {

    @Autowired
    private LeaderboardService leaderboardService;

    @GetMapping
    public ResponseEntity<?> getLeaderboardForExam(@Valid @RequestBody LeaderboardRequestModel leaderboardRequestModel){
        ResponseModel<List<LeaderboardResponseModel>> response = leaderboardService.fetchLeaderboardForExam(leaderboardRequestModel);

        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @GetMapping("/branch")
    public ResponseEntity<?> getLeaderboardForExamAndBranch(@Valid @RequestBody LeaderboardRequestModel leaderboardRequestModel){
        ResponseModel<List<LeaderboardResponseModel>> response = leaderboardService.fetchLeaderboardForExamAndBranch(leaderboardRequestModel);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
