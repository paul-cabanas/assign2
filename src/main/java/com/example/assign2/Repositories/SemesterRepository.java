package com.example.assign2.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.example.assign2.Entities.Semester;


    public interface SemesterRepository extends JpaRepository <Semester, Integer> {
}
