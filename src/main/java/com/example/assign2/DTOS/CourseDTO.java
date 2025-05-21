package com.example.assign2.DTOS;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CourseDTO {

 // @JsonProperty("emp_id")
    private String courseID;
    private String cName;
    private int credits;
}


