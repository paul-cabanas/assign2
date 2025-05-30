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
public class StudentId implements Serializable {
    private String stdNo;

    public StudentId() {
    }

    public StudentId(String stdNo) {
        this.stdNo = stdNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof StudentId))
            return false;
        StudentId that = (StudentId) o;
        return stdNo.equals(that.stdNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stdNo);
    }
}
