package com.example.demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "roll_no", nullable = false)
    private Integer rollNo;

    @Column(name = "student_name", nullable = false)
    private String name;

    @Column(name = "branch_name", nullable = false)
    private String branch;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
                "id=" + id +
                ", rollNo=" + rollNo +
                ", name='" + name + '\'' +
                ", branch='" + branch + '\'' +
                '}';
    }
}
