/* 
 * Assignment 2 - Web Engineering
 * John Villegas (c3476534)
 * Paul Cabanas (c3481070)
 */

package com.example.assign2.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.assign2.Entities.CourseOfferings;
import com.example.assign2.Entities.Enrol;
import com.example.assign2.Entities.EnrolId;

import java.util.List;
import java.util.Optional;

public interface EnrolRepository extends JpaRepository<Enrol, EnrolId> {
    List<Enrol> findByStdNoAndSemesterID(String stdNo, Integer semester);
}
