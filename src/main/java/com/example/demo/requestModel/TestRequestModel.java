package com.example.demo.requestModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TestRequestModel implements Serializable {

    @NotNull(message = "roll no can't be missing or empty")
    private Integer rollNo;

    @NotBlank(message = "name can't be missing or empty")
    private String name;

    @NotNull(message = "branch can't be missing or empty")
    private String branch;

    @NotNull(message = "exam id can't be missing or empty")
    private Integer examId;

    private List<QuestionAnswerRequestModel> questionAnswers;
}
