package com.example.demo.requestModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StudentRequestModel {

    @NotNull(message = "rollNo can't be empty or null")
    private Integer rollNo;

    @NotBlank(message = "name can't be empty or null")
    private String name;

    @NotBlank(message = "branch can't be empty or null")
    private String branch;

    public Integer getRollNo() {
        return rollNo;
    }

    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Student{" +
                ", rollNo=" + rollNo +
                ", name='" + name + '\'' +
                ", branch='" + branch + '\'' +
                '}';
    }
}
