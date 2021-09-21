package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum ExamCategoryTypeEnum {

    PROGRAMMING("Programming", 1),

    GENERAL_KNOWLEDGE("General Knowledge", 2);

    private String examCategoryType;

    private Integer examCategoryTypeCode;

    public void setExamCategoryType(String examCategoryType) {
        this.examCategoryType = examCategoryType;
    }

    public void setExamCategoryTypeCode(Integer examCategoryTypeCode) {
        this.examCategoryTypeCode = examCategoryTypeCode;
    }
}
