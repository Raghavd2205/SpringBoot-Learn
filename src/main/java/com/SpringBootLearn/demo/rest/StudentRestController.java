package com.SpringBootLearn.demo.rest;

import com.SpringBootLearn.demo.dao.StudentDAO;
import com.SpringBootLearn.demo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentRestController {
    private final StudentDAO studentDAO;
    public StudentRestController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }
    @GetMapping("/students")
    public List<Student> readAllStudent() {
        List<Student> theStudent = studentDAO.findAll();
        return theStudent;
    }
}
