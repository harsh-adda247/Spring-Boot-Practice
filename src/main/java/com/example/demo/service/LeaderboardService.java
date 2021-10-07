package com.example.demo.service;

import com.example.demo.requestModel.LeaderboardRequestModel;
import com.example.demo.responseModel.LeaderboardResponseModel;
import com.example.demo.responseModel.ResponseModel;

import java.util.List;

public interface LeaderboardService {

    /**
     * method to fetch leaderboard for an exam regardless of the branch
     *
     * @param leaderboardRequestModel
     * @return
     */
    public ResponseModel<List<LeaderboardResponseModel>> fetchLeaderboardForExam(
            LeaderboardRequestModel leaderboardRequestModel);

    /**
     * method to fetch leaderboard for an exam, corresponding to a specific branch
     *
     * @param leaderboardRequestModel
     * @return
     */
    public ResponseModel<List<LeaderboardResponseModel>> fetchLeaderboardForExamAndBranch(
            LeaderboardRequestModel leaderboardRequestModel);
}
