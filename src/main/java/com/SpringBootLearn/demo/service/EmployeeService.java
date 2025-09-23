package com.SpringBootLearn.demo.service;

import com.SpringBootLearn.demo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findEmployeeById(Integer Id);
    void addEmployee(Employee theEmployee);
}
