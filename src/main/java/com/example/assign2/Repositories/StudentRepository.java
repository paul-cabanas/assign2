/* 
 * Assignment 2 - Web Engineering
 * John Villegas (c3476534)
 * Paul Cabanas (c3481070)
 */

package com.example.assign2.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.assign2.Entities.Student;
import com.example.assign2.Entities.StudentId;

public interface StudentRepository extends JpaRepository<Student, StudentId> {
    // This interface extends JpaRepository to provide CRUD operations for the Student entity.
    // The first parameter is the entity type, and the second parameter is the type of the entity's ID.
    
}
