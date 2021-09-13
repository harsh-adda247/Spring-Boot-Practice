package com.example.demo.requestModel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.Map;

public class QuestionsRequestModel {

    @Null(message = "id not allowed")
    private Integer id;

    @NotBlank(message = "question can't be missing or empty")
    private String question;

    @NotBlank(message = "options can't be missing or empty")
    @Max(value = 4)
    @Min(value = 4)
    private Map<String, Boolean> options;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Map<String, Boolean> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Boolean> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "ExamRequestModel{" +
                "question='" + question + '\'' +
                ", options=" + options + '}';
    }
}
