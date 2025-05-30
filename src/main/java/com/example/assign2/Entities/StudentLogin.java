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
public class StudentLogin {

    @Id
    @Column(name = "stdNo")
    private String stdNo;

    @Column(name = "passwordSalt")
    private String passwordSalt;

    @Column(name = "passwordHash")
    private String hashedPassword;

    // Getters and Setters
}
