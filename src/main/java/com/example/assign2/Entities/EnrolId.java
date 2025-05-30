/* 
 * Assignment 2 - Web Engineering
 * John Villegas (c3476534)
 * Paul Cabanas (c3481070)
 */

package com.example.assign2.Entities;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;

@Embeddable
public class EnrolId implements Serializable {

    private String stdNo;
    private String courseID;
    private Integer semesterID;

    public EnrolId() {
    }

    public EnrolId(String stdNo, String courseID, Integer semesterID) {
        this.stdNo = stdNo;
        this.courseID = courseID;
        this.semesterID = semesterID;
    }

    // equals() and hashCode() are essential for composite keys
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof EnrolId))
            return false;
        EnrolId that = (EnrolId) o;
        return stdNo.equals(that.stdNo) &&
                courseID.equals(that.courseID) &&
                semesterID.equals(that.semesterID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stdNo, courseID, semesterID);
    }

    // Getters & setters
}
