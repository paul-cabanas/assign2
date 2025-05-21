package com.example.assign2.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.assign2.Entities.CourseOfferings;
import com.example.assign2.Entities.CourseOfferingId;

public interface CourseOfferingRepository extends JpaRepository<CourseOfferings, CourseOfferingId> {
    List<CourseOfferings> findBySemesterSemesterId(Integer semesterID); 
}