package com.example.assign2.DTOS;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EnrolDTO {
    private String stdNo;
    private String courseID;
    private Integer semesterID;
    private String credits; 
    private Double marks;
}
