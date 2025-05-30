/* 
 * Assignment 2 - Web Engineering
 * John Villegas (c3476534)
 * Paul Cabanas (c3481070)
 */

package com.example.assign2.DTOS;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentRequestDTO {
    @Length(min = 3, max = 4)
    @NotBlank(message = "Student ID cannot be null or empty and must be between 3 and 4 characters")
    private String stdNo;

    private String givenNames;

    @NotBlank(message = "Lastname cannot be null or empty")
    private String lastName;

    @NotBlank(message = "Password cannot be null or empty")
    private String password;
}
