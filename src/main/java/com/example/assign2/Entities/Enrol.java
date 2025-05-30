/* 
 * Assignment 2 - Web Engineering
 * John Villegas (c3476534)
 * Paul Cabanas (c3481070)
 */

package com.example.assign2.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@IdClass(EnrolId.class)
@Table(name = "StudentCourseRegistration")
public class Enrol {

    @Id
    @Column(name = "std_No")
    private String stdNo;

    @Id
    @Column(name = "course_ID")
    private String courseID;

    @Id
    @Column(name = "semester_ID")
    private Integer semesterID;

    @Column(name = "grade")
    private String grade;

    @Column(name = "mark")
    private Double mark;

    public Enrol(String stdNo, String courseID, Integer semesterID) {
        this.stdNo = stdNo;
        this.courseID = courseID;
        this.semesterID = semesterID;
    }
}
