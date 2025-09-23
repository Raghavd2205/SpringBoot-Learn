package com.SpringBootLearn.demo.rest;

import com.SpringBootLearn.demo.dao.StudentDAO;
import com.SpringBootLearn.demo.entity.Student;
import com.SpringBootLearn.demo.entity.StudentErrorResponse;
import com.SpringBootLearn.demo.exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StudentRestController {
    private final StudentDAO studentDAO;
    public StudentRestController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }
    //Spring Boot uses jackson to convert object into json object in response
    @GetMapping("/students")
    public List<Student> readAllStudent() {
        List<Student> theStudent = studentDAO.findAll();
        return theStudent;
    }
    @GetMapping("/students/{studentId}")
    public Student readStudentById(@PathVariable int studentId) {
        Student theStudent = studentDAO.findById(studentId);
        if(theStudent == null){
            throw new StudentNotFoundException("Student id not found - "+ studentId);
        }
        return theStudent;
    }

}
