package com.SpringBootLearn.demo.rest;

import com.SpringBootLearn.demo.dao.EmployeeDAO;
import com.SpringBootLearn.demo.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeRestController {
    private EmployeeDAO employeeDAO;

    public EmployeeRestController(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @GetMapping("/employee")
    public List<Employee> listAllEmployee(){
        List<Employee> theEmployees = employeeDAO.findAll();
        return theEmployees;
    }
}
