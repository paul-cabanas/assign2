/* 
 * Assignment 2 - Web Engineering
 * John Villegas (c3476534)
 * Paul Cabanas (c3481070)
 */

package com.example.assign2.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.assign2.DTOS.StudentResponseDTO;
import com.example.assign2.Entities.PasswordSecurity;
import com.example.assign2.Entities.Student;
import com.example.assign2.DTOS.StudentRequestDTO;
import com.example.assign2.Repositories.StudentRepository;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;

    public StudentResponseDTO addStudent(StudentRequestDTO studentRequestDTO) {
        Double passwordSalt = 0.57;
        var student = new Student(
            studentRequestDTO.getStdNo(),
            studentRequestDTO.getLastName(),
            studentRequestDTO.getGivenNames(),
            PasswordSecurity.hashPassword(studentRequestDTO.getPassword(), passwordSalt),
            passwordSalt);

        var savedStudent = studentRepository.save(student);
        return new StudentResponseDTO(
            savedStudent.getStdNo(),
            savedStudent.getGivenNames(),
            savedStudent.getLastName());
    }
}
