package com.example.demo.responseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LeaderboardResponseModel {

    private String name;

    private Integer rollNo;

    private String branch;

    private Integer marks;

    private Integer examId;
}
