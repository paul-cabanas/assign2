package com.example.assign2.DTOS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterEnrolmentRequest {
    @NotBlank(message = "Student Number is required")
    private String stdNo;
    
    @NotBlank(message = "Course ID is required")
    private String courseID;

    @NotBlank(message = "SemesterID is required")
    private int semesterID;
    
}
