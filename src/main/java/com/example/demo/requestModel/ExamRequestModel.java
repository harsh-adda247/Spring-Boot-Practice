package com.example.demo.requestModel;

import javax.validation.constraints.*;
import java.util.List;

public class ExamRequestModel {

    @Null(message = "id not allowed")
    private Integer id;

    @NotNull(message = "questions are missing or empty")
    @Min(10)
    @Max(10)
    private List<QuestionsRequestModel> questions;

    @NotBlank(message = "start date missing or empty")
    private String startDate;

    @NotBlank(message = "end date missing or empty")
    private String endDate;

    public List<QuestionsRequestModel> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionsRequestModel> questions) {
        this.questions = questions;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "ExamRequestModel{" +
                "questions=" + questions +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
