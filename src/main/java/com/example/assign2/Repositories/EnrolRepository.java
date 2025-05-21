package com.example.assign2.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.example.assign2.Entities.Enrol;

public interface EnrolRepository extends JpaRepository <Enrol, String> {
    // Custom query methods can be defined here if needed
    // For example, you can add methods to find enrollments by student ID or course ID
    // List<Enrol> findByStudentId(Integer studentId);
    // List<Enrol> findByCourseId(String courseId);
}
