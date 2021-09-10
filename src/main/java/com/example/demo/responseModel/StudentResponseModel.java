package com.example.demo.responseModel;

public class StudentResponseModel {
    private Integer rollNo;

    private String name;

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
