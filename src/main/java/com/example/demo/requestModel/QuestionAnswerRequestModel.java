package com.example.demo.requestModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionAnswerRequestModel {

    @NotNull(message = "question id is missing or empty")
    @Min(1)
    private Integer questionId;

    @NotBlank(message = "answer is missing or empty")
    private Integer answer;
}
