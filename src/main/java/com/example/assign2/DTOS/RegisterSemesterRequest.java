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
public class RegisterSemesterRequest {
    private Integer id;

    @NotBlank(message = "Name is required")
    private int semester;

    @NotBlank(message = "Year is required")
    private int year;

    @NotBlank(message = "Open for enrolment is required")
    private boolean openForEnrolment;
}
