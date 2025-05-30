/* 
 * Assignment 2 - Web Engineering
 * John Villegas (c3476534)
 * Paul Cabanas (c3481070)
 */

package com.example.assign2.Repositories;


import com.example.assign2.Entities.StudentId;
import com.example.assign2.Entities.StudentLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentLoginRepository extends JpaRepository<StudentLogin, StudentId> {
    // String is the type of the primary key (stdNo)
}
