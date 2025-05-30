/* 
 * Assignment 2 - Web Engineering
 * John Villegas (c3476534)
 * Paul Cabanas (c3481070)
 */

package com.example.assign2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.assign2.DTOS.StudentRequestDTO;
import com.example.assign2.Entities.Course;
import com.example.assign2.Entities.CourseOfferings;
import com.example.assign2.Entities.Semester;
import com.example.assign2.Entities.Student;
import com.example.assign2.Entities.StudentId;
import com.example.assign2.Repositories.CourseOfferingRepository;
import com.example.assign2.Repositories.SemesterRepository;
import com.example.assign2.Repositories.StudentRepository;
import com.example.assign2.Services.StudentService;

import java.util.List;

import static org.mockito.Mockito.*;

public class StudentServiceTest {

    private StudentService studentService;
    private StudentRepository studentRepository;
    private StudentRequestDTO studentRequestDTO;
    private CourseOfferingRepository courseOfferingRepository;
    private StudentRepository studentRepo;
    private SemesterRepository semesterRepo;
    private StudentId studentId;

    @BeforeEach
    public void setup() {
        courseOfferingRepository = mock(CourseOfferingRepository.class);
        studentRepo = mock(StudentRepository.class);
        semesterRepo = mock(SemesterRepository.class);
        studentService = mock(StudentService.class);
        studentId = mock(StudentId.class);
    }

    @Test
    public void testGetCoursesWithEnrolmentStatus_returnsCorrectEnrolment() {
        Student student = new Student("c600", "Doe", "John", "hashedPassword", 0.57);
        Semester semester = new Semester(1, 2026, true);
        StudentId studentId = new StudentId("c600");
        Course course = new Course("COMP1140", "Programming", 10);
        CourseOfferings offering = new CourseOfferings(semester, course, 100);

        when(studentRepo.findById(studentId)).thenReturn(java.util.Optional.of(student));
        when(semesterRepo.findById(1)).thenReturn(java.util.Optional.of(semester));
        when(courseOfferingRepository.findBySemesterSemesterId(103)).thenReturn(List.of(offering));
        when(semesterRepo.findById(1)).thenReturn(java.util.Optional.of(semester));
    }
}
