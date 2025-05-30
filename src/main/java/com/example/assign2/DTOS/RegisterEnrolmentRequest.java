/* 
 * Assignment 2 - Web Engineering
 * John Villegas (c3476534)
 * Paul Cabanas (c3481070)
 */

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

    @NotNull(message = "SemesterID is required")
    private Integer semesterID;

    @NotNull(message = "Year is required")
    private boolean enrolled;

}
