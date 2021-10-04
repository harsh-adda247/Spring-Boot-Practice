package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "exam_test")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer rollNo;

    private String name;

    private Integer examId;

    private String branch;

    private Integer marks;
}
