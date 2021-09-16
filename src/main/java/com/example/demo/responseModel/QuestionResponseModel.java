package com.example.demo.responseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionResponseModel {

    private String questionl;

    private List<String> options;

    private String answer;
}
