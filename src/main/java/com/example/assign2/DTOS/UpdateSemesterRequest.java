/* 
 * Assignment 2 - Web Engineering
 * John Villegas (c3476534)
 * Paul Cabanas (c3481070)
 */

package com.example.assign2.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateSemesterRequest {

    private Integer semester;
    private Integer year;
    private boolean openForEnrolment;

}
