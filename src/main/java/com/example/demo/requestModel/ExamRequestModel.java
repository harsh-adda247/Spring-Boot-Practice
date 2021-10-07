package com.example.demo.requestModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExamRequestModel {

    @Null(message = "id not allowed")
    private Integer id;

    @NotNull(message = "exam category can't be missing or empty")
    @Min(value = 1, message = "exam category can't be less than 1")
    @Max(value = 2, message = "exam category can't be greater than 2")
    private Integer examCategory;

    @NotBlank(message = "name can't be missing or empty")
    private String name;

    @Null(message = "examId not allowed")
    private Integer examId;

    @NotBlank(message = "start date missing or empty")
    private String startDate;

    @NotBlank(message = "end date missing or empty")
    private String endDate;

}
