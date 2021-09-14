package com.example.demo.requestModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExamRequestModel {

    @Null(message = "id not allowed")
    private Integer id;

    @NotBlank(message = "start date missing or empty")
    private String startDate;

    @NotBlank(message = "end date missing or empty")
    private String endDate;

}
