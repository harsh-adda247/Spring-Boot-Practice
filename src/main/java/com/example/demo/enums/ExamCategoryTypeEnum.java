package com.example.demo.enums;

public enum ExamCategoryTypeEnum {

    PROGRAMMING("Programming", 1),

    GENERAL_KNOWLEDGE("General Knowledge", 2);

    private String examCategoryType;

    private Integer examCategoryTypeCode;

    /**
     * constructor to instantiate a new exam category type enum
     * @param examCategoryType
     * @param examCategoryTypeCode
     */
    ExamCategoryTypeEnum(String examCategoryType, Integer examCategoryTypeCode) {
        this.examCategoryType = examCategoryType;
        this.examCategoryTypeCode = examCategoryTypeCode;
    }

    public String getExamCategoryType() {
        return examCategoryType;
    }

    public void setExamCategoryType(String examCategoryType) {
        this.examCategoryType = examCategoryType;
    }

    public Integer getExamCategoryTypeCode() {
        return examCategoryTypeCode;
    }

    public void setExamCategoryTypeCode(Integer examCategoryTypeCode) {
        this.examCategoryTypeCode = examCategoryTypeCode;
    }

    @Override
    public String toString() {
        return "ExamCategoryTypeEnum{" +
                "examCategoryType='" + examCategoryType + '\'' +
                ", examCategoryTypeCode=" + examCategoryTypeCode +
                '}';
    }
}
