package com.example.assign2.Student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;

@Builder
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stdNo")
    private Long id;

    @Column(name = "lastname")
    private String name;

    @Column(name = "givenNames")
    private String givenNames;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGivenNames() {
        return givenNames;
    }

    public void setGivenNames(String givenNames) {
        this.givenNames = givenNames;
    }

    public char getPassedwordHash() {
        return passedwordHash;
    }

    public void setPassedwordHash(char passedwordHash) {
        this.passedwordHash = passedwordHash;
    }

    public char getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(char passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    @Column(name = "passwordHash")
    private char passedwordHash;

    @Column(name="passwordSalt")
    private char passwordSalt;
}
