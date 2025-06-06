/* 
 * Assignment 2 - Web Engineering
 * John Villegas (c3476534)
 * Paul Cabanas (c3481070)
 */

package com.example.assign2.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@IdClass(StudentId.class)
@Table(name = "Student")
public class Student {
    @Id
    @Column(name = "stdNo")
    private String stdNo;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "givenNames")
    private String givenNames;

    @Column(name = "passwordHash")
    private String passwordHash;

    @Column(name = "passwordSalt")
    private Double passwordSalt;

    public Student(String stdNo, String lastName, String givenNames) {
        this.stdNo = stdNo;
        this.lastName = lastName;
        this.givenNames = givenNames;
    }
}
