package com.example.assign2.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "StudentCourseRegistration")
public class Enrol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stdNo")
    private String stdNo;

    @Column(name = "courseID")
    private String courseID;

    @Column(name = "semesterID")
    private Integer semesterID;
 
    @Column(name = "grade")
    private String grade;

    @Column (name = "mark")
    private double mark;

    public Enrol(String stdNo, String courseID, Integer semesterID){
        this.stdNo = stdNo;
        this.courseID = courseID;
        this.semesterID = semesterID;
    }
}


