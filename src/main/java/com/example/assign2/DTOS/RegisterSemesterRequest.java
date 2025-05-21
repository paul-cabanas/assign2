package com.example.assign2.DTOS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class RegisterSemesterRequest {
    @NotNull
    private Integer id;
    
    @NotBlank(message = "Name is required")
    private String semester;
}

