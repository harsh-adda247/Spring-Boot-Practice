package com.example.demo.requestModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LeaderboardRequestModel implements Serializable {

    @NotNull(message = "examId can't be missing or empty")
    private Integer examId;

    private String branch;
}
