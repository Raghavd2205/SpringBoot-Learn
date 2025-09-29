package com.SpringBootLearn.demo.dao;

import com.SpringBootLearn.demo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findEmployeeById(Integer Id);
    Employee addEmployee(Employee theEmployee);
    void delete(Employee theEmployee);
}
