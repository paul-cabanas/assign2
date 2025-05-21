package com.example.assign2.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.example.assign2.Repositories.CourseOfferingRepository;
import com.example.assign2.Repositories.CourseRepository;
import com.example.assign2.Repositories.SemesterRepository;
import com.example.assign2.Repositories.EnrolRepository;
import com.example.assign2.DTOS.CourseDTO;
import com.example.assign2.DTOS.EnrolDTO;
import com.example.assign2.DTOS.RegisterEnrolmentRequest;
import com.example.assign2.DTOS.RegisterSemesterRequest;
import com.example.assign2.DTOS.SemesterDTO;
import com.example.assign2.DTOS.UpdateSemesterRequest;
import com.example.assign2.Entities.Semester;
import com.example.assign2.Entities.Enrol;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@AllArgsConstructor
@RequestMapping("/unix")
public class Controller {


    private final SemesterRepository semesterRepository;
    private final CourseOfferingRepository courseOfferingRepository;
    private final EnrolRepository enrolRepository;

    @RequestMapping
    public String HomePage()
    {
        return "This is SENG2050 Assignment 2!<br>"+
               "add '/semesters' to the URL to see all semesters<br>"+
               "add '/semesters/-semesterid-/courses' to the URL to see all courses for that semester<br>";
    }

     @GetMapping("/semesters")
    public List<SemesterDTO> getAllSemesters(
        @RequestHeader(required = false, name = "x-auth-token") String authToken,
        @RequestParam(required = false, defaultValue="", name="param1") String parameter
    )
    {
        System.out.println("Authentication token:" + authToken);
        System.out.println("Parameter passed: " + parameter);
        return semesterRepository.findAll()
                        .stream()
                        .map(semester -> new SemesterDTO(
                            semester.getSemesterId(),
                            semester.getSemester(),
                            semester.getYear(),
                            semester.getOpenForEnrolment()
                        ))
                        .toList();
    }

   @PostMapping("/semesters")
    public SemesterDTO createSemester(@Valid @RequestBody RegisterSemesterRequest registerSemester) {

        // Create an employee object and save
        Semester semester = new Semester(registerSemester.getSemester(), 
                                        registerSemester.getYear(), 
                                        registerSemester.isOpenForEnrolment());
        var newSemester = semesterRepository.save(semester);

        // Return an EmployeeDto with the created employee 
        var semesterDTO =  new SemesterDTO(newSemester.getSemesterId(), 
                                           newSemester.getSemester(),
                                             newSemester.getYear(),
                                                newSemester.getOpenForEnrolment()
        );
        return semesterDTO;
    }

     @PutMapping("semesters/{id}")
    public ResponseEntity<UpdateSemesterRequest> updateSemester(
                    @PathVariable(name="id") Integer id, 
                    @RequestBody UpdateSemesterRequest updatedSemester) {
        
        // Finding the employee
        var semester = semesterRepository.findById(id).orElse(null);
        if (semester == null)
        {
            return new ResponseEntity<UpdateSemesterRequest>(HttpStatus.NOT_FOUND);
        }
        
        // Updating the fields and saving
        semester.setSemesterId(updatedSemester.getId());
        semester.setSemester(updatedSemester.getSemester());
        semester.setYear(updatedSemester.getYear());
        semester.setOpenForEnrolment(updatedSemester.isOpenForEnrolment());
        var savedSemester = semesterRepository.save(semester);

        return new ResponseEntity<UpdateSemesterRequest>(
                        new UpdateSemesterRequest(savedSemester.getSemesterId(), savedSemester.getSemester(), 
                                savedSemester.getYear(), savedSemester.getOpenForEnrolment()), HttpStatus.OK);
    }

    @GetMapping("semesters/{id}")
    public ResponseEntity<SemesterDTO> getSemester(@PathVariable Integer id)
    {
        var semester = semesterRepository.findById(id).orElse(null);
        if (semester == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<SemesterDTO>(new SemesterDTO(semester.getSemesterId(),
                                                                semester.getSemester(),
                                                                semester.getYear(),
                                                                semester.getOpenForEnrolment()),
                                                            HttpStatus.OK);
    }

    @GetMapping("semesters/{semesterId}/courses")
    public ResponseEntity<List<CourseDTO>> getCoursesInSemester(@PathVariable Integer semesterId) {
    var offerings = courseOfferingRepository.findBySemesterSemesterId(semesterId);

    //Return 404 if no offerings found
    if (offerings.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Creates list of CourseDTOs from the course offerings
    List<CourseDTO> courseList = offerings.stream()
        .map(offering -> {
            var course = offering.getCourse();
            return new CourseDTO(course.getCourseId(), course.getCName(), course.getCredits());
        })
        .toList();
    // Return the list of courses with a 200 OK status
    return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @PostMapping("/enrol")
    public EnrolDTO enrolStudent(@Valid @RequestBody RegisterEnrolmentRequest registerEnrolment) {

        // Create an employee object and save
        Enrol enrolment = new Enrol(registerEnrolment.getStdNo(), 
                                    registerEnrolment.getCourseID(), 
                                    registerEnrolment.getSemesterID());
        var newEnrolment = enrolRepository.save(enrolment);

        // Return an EmployeeDto with the created employee 
        var enrolmentDTO =  new EnrolDTO(newEnrolment.getStdNo(), 
                                           newEnrolment.getCourseID(),
                                             newEnrolment.getSemesterID(),
                                                newEnrolment.getGrade(),
                                                newEnrolment.getMark()
        );
        return enrolmentDTO;
    }
    

} 

