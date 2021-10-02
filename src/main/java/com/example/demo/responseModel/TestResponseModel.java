package com.example.demo.responseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TestResponseModel {

    private String name;

    private String branch;

    private Integer marks;
}
