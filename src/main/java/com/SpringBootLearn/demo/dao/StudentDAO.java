package com.SpringBootLearn.demo.dao;

import com.SpringBootLearn.demo.entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student theStudent);
    Student findById(Integer Id);
    List<Student> findAll();
    List<Student> findByLastName(String theLastName);
}
