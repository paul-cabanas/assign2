/* 
 * Assignment 2 - Web Engineering
 * John Villegas (c3476534)
 * Paul Cabanas (c3481070)
 */

package com.example.assign2.Entities;

import java.io.Serializable;
import java.util.Objects;

public class CourseOfferingId implements Serializable {
    private Integer semester;
    private String course;

    public CourseOfferingId() {
    }

    public CourseOfferingId(Integer semester, String course) {
        this.semester = semester;
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CourseOfferingId))
            return false;
        CourseOfferingId that = (CourseOfferingId) o;
        return Objects.equals(semester, that.semester) &&
                Objects.equals(course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(semester, course);
    }
}

/*
 * keytool -genkey -alias myserver -keyalg RSA -keystore keystore.jks -storepass
 * P@ssword1 -validity 365
 */
