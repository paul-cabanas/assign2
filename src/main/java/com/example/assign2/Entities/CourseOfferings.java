package com.example.assign2.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CourseOfferings")
@Builder
@IdClass(CourseOfferingId.class)
public class CourseOfferings {

    @Id
    @ManyToOne
    @JoinColumn(name = "semesterID", referencedColumnName = "semesterID")
    private Semester semester;

    @Id
    @ManyToOne
    @JoinColumn(name = "courseID", referencedColumnName = "courseID")
    private Course course;


    @Column(name = "max_capacity")
    private Integer maxCapacity;
}