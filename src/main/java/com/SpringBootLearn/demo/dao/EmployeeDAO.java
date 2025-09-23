package com.SpringBootLearn.demo.dao;

import com.SpringBootLearn.demo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
}
