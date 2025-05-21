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
@Table(name = "Semester")
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "semesterID")
    private Integer semesterId;

    @Column(name = "semester")
    private Integer semester;

    @Column(name = "year")
    private Integer year;
 
    @Column(name = "open_for_enrolment")
    private Integer openForEnrolment;

    public Semester(int semesterId)
    {
        this.semesterId = semesterId; 
    }
}

