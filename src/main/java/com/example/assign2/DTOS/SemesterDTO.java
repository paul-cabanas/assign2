/* 
 * Assignment 2 - Web Engineering
 * John Villegas (c3476534)
 * Paul Cabanas (c3481070)
 */

package com.example.assign2.DTOS;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SemesterDTO {
    // @JsonProperty("emp_id")
    private Integer id;
    private Integer semester;
    private Integer year;
    private Boolean openForEnrolment;
}
