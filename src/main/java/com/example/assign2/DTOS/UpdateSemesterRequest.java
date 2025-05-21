package com.example.assign2.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateSemesterRequest {

    private Integer id;
    private Integer semester;
    private Integer year;
    private boolean openForEnrolment;

}

