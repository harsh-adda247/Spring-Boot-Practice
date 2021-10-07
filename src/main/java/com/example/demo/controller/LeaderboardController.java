package com.example.demo.controller;

import com.example.demo.requestModel.LeaderboardRequestModel;
import com.example.demo.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/leaderboard/exam")
public class LeaderboardController {

    @Autowired
    private LeaderboardService leaderboardService;

    public ResponseEntity<?> getLeaderboardForExam(@Valid LeaderboardRequestModel leaderboardRequestModel){
        return null;
    }

    @GetMapping("/branch")
    public ResponseEntity<?> getLeaderboardForExamAndBranch(@Valid LeaderboardRequestModel leaderboardRequestModel){
        return null;
    }
}
