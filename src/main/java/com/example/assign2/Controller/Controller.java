/* 
 * Assignment 2 - Web Engineering
 * John Villegas (c3476534)
 * Paul Cabanas (c3481070)
 */

package com.example.assign2.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;

import com.example.assign2.Repositories.CourseOfferingRepository;
import com.example.assign2.Repositories.CourseRepository;
import com.example.assign2.Repositories.SemesterRepository;
import com.example.assign2.Repositories.StudentLoginRepository;
import com.example.assign2.Services.StudentService;
import com.example.assign2.Util.JwtUtil;
import com.example.assign2.Repositories.EnrolRepository;
import com.example.assign2.DTOS.CourseDTO;
import com.example.assign2.DTOS.EnrolDTO;
import com.example.assign2.DTOS.RegisterEnrolmentRequest;
import com.example.assign2.DTOS.RegisterSemesterRequest;
import com.example.assign2.DTOS.SemesterDTO;
import com.example.assign2.DTOS.StudentRequestDTO;
import com.example.assign2.DTOS.StudentResponseDTO;
import com.example.assign2.DTOS.UpdateSemesterRequest;
import com.example.assign2.Entities.Semester;
import com.example.assign2.Entities.Student;
import com.example.assign2.Entities.StudentId;
import com.example.assign2.Entities.StudentLogin;
import com.example.assign2.Entities.Enrol;
import com.example.assign2.Entities.PasswordSecurity;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
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
    private final StudentLoginRepository studentLoginRepository;

    @Autowired
    private StudentService studentService;

    //POST: SEMESTER REGISTER
    @PostMapping("/student/register")
    public ResponseEntity<StudentResponseDTO> addStudent(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        var student = studentService.addStudent(studentRequestDTO);
        return new ResponseEntity<StudentResponseDTO>(student, HttpStatus.OK);
    }


     //POST: STUDENT LOGIN
    @PostMapping("/student/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> user, HttpServletResponse response) {
        String stdNo = user.get("stdNo");
        String inputPassword = user.get("password");

        StudentId studentId = new StudentId(stdNo);
        Optional<StudentLogin> loginOpt = studentLoginRepository.findById(studentId);

        if (loginOpt.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Student not found!"));
        }

        StudentLogin login = loginOpt.get();
        String expectedHash = login.getHashedPassword(); // from DB
        String inputHash = PasswordSecurity.hashPassword(inputPassword, 0.57);

        if (!expectedHash.equals(inputHash)) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid credentials"));
        }

        // Password is correct — generate JWTs, cookies etc.
        String accessToken = JwtUtil.generateAccessToken(stdNo);
        String refreshToken = JwtUtil.generateRefreshToken(stdNo);

        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/unix/student/refresh");
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of("token", accessToken));
    }



     //POST: STUDENT LOGOUT
    @PostMapping("/student/logout")
    public ResponseEntity<?> logout(HttpServletResponse response, HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("refreshToken")) {
                JwtUtil.invalidateRefreshToken(cookie.getValue());
                cookie.setMaxAge(0);
                cookie.setPath("/api");
                response.addCookie(cookie);
                break;
            }
        }
        return ResponseEntity.ok("Logged out");
    }

     //POST: STUDENT REFRESH
    @PostMapping("/student/refresh")
    public ResponseEntity<?> refresh(HttpServletRequest request) {

        // Find the refreshToken cookie
        String refreshToken = null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("refreshToken")) {
                refreshToken = cookie.getValue();
                break;
            }
        }

        if (refreshToken == null)
            return ResponseEntity.status(401).body("Missing refresh token");

        // Check whether refresh token in valid
        String username = JwtUtil.validateRefreshToken(refreshToken);
        if (username == null)
            return ResponseEntity.status(401).body("Invalid refresh token");

        String newAccessToken = JwtUtil.generateAccessToken(username);
        return ResponseEntity.ok(Map.of("token", newAccessToken));
    }

    // GET: SEMESTER
    @GetMapping("/semesters")
    public List<SemesterDTO> getAllSemesters(
            @RequestHeader(required = false, name = "x-auth-token") String authToken,
            @RequestParam(required = false, defaultValue = "", name = "param1") String parameter) {
        System.out.println("Authentication token:" + authToken);
        System.out.println("Parameter passed: " + parameter);
        return semesterRepository.findAll()
                .stream()
                .map(semester -> new SemesterDTO(
                        semester.getSemesterId(),
                        semester.getSemester(),
                        semester.getYear(),
                        semester.getOpenForEnrolment()))
                .toList();
    }

    
    // PUT SEMESTER
    @PutMapping("semesters/{id}")
    public ResponseEntity<UpdateSemesterRequest> updateSemester(
            @PathVariable(name = "id") Integer id,
            @RequestBody UpdateSemesterRequest updatedSemester) {

        // Finding the employee
        var semester = semesterRepository.findById(id).orElse(null);
        if (semester == null) {
            return new ResponseEntity<UpdateSemesterRequest>(HttpStatus.NOT_FOUND);
        }

        // Updating the fields and saving
        semester.setSemester(updatedSemester.getSemester());
        semester.setYear(updatedSemester.getYear());
        semester.setOpenForEnrolment(updatedSemester.isOpenForEnrolment());
        var savedSemester = semesterRepository.save(semester);

        return new ResponseEntity<UpdateSemesterRequest>(
                new UpdateSemesterRequest(savedSemester.getSemester(),
                        savedSemester.getYear(), savedSemester.getOpenForEnrolment()),
                HttpStatus.OK);
    }


    // GET: SEMESTER
    @GetMapping("semesters/{id}")
    public ResponseEntity<SemesterDTO> getSemester(@PathVariable Integer id) {
        var semester = semesterRepository.findById(id).orElse(null);
        if (semester == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<SemesterDTO>(new SemesterDTO(semester.getSemesterId(),
                semester.getSemester(),
                semester.getYear(),
                semester.getOpenForEnrolment()),
                HttpStatus.OK);
    }


    // GET: SEMESTER COURSES
    @GetMapping("semesters/GetCourses")
    public ResponseEntity<?> getCoursesInSemester(
            @RequestParam String stdNo,
            @RequestParam Integer semester,
            @RequestHeader("auth") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        System.out.println("Authentication token:" + authHeader);

        var offerings = courseOfferingRepository.findBySemesterSemesterId(semester);
        if (offerings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Enrol> enrolments = enrolRepository.findByStdNoAndSemesterID(stdNo, semester);

        Set<String> enrolledCourseIds = enrolments.stream()
                .map(Enrol::getCourseID)
                .collect(Collectors.toSet());

        List<Map<String, Object>> courseList = offerings.stream()
                .map(offering -> {
                    String courseId = offering.getCourse().getCourseId();
                    Map<String, Object> courseMap = new HashMap<>();
                    courseMap.put("courseNo", courseId);
                    courseMap.put("enrolled", enrolledCourseIds.contains(courseId));
                    return courseMap;
                })
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("studentNo", stdNo);
        response.put("semesterId", semester);
        response.put("courseList", courseList);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    // GET: ENROL
    @GetMapping("/enrol")
    public List<EnrolDTO> getAllEnrolments(
            @RequestHeader(required = false, name = "x-auth-token") String authToken,
            @RequestParam(required = false, defaultValue = "", name = "param1") String parameter) {
        System.out.println("Authentication token:" + authToken);
        System.out.println("Parameter passed: " + parameter);
        return enrolRepository.findAll()
                .stream()
                .map(enrol -> new EnrolDTO(
                        enrol.getStdNo(),
                        enrol.getCourseID(),
                        enrol.getSemesterID(),
                        enrol.getGrade(),
                        enrol.getMark()))
                .toList();
    }


    // POST: ENROL
    @PostMapping("/enrol")
    public EnrolDTO enrolStudent(@Valid @RequestBody RegisterEnrolmentRequest registerEnrolment) {

        // Create an enrolment object and save
        Enrol enrolment = new Enrol(registerEnrolment.getStdNo(),
                registerEnrolment.getCourseID(),
                registerEnrolment.getSemesterID());
        var newEnrolment = enrolRepository.save(enrolment);

        // Return an EmployeeDto with the created employee
        var enrolmentDTO = new EnrolDTO(newEnrolment.getStdNo(),
                newEnrolment.getCourseID(),
                newEnrolment.getSemesterID(),
                newEnrolment.getGrade(),
                newEnrolment.getMark());
        return enrolmentDTO;
    }

}
