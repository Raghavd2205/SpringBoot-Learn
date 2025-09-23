package com.SpringBootLearn.demo.rest;

import com.SpringBootLearn.demo.dao.EmployeeDAO;
import com.SpringBootLearn.demo.entity.Employee;
import com.SpringBootLearn.demo.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeRestController {
    // we need to use service layer in rest controller instead of dao layer, dao layer needs to be used in service layer
    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee")
    public List<Employee> listAllEmployee(){
        List<Employee> theEmployees = employeeService.findAll();
        return theEmployees;
    }
    @GetMapping("/employee/{employeeId}")
    public Employee listEmployeeById(@PathVariable int employeeId){
        return employeeService.findEmployeeById(employeeId);
    }
}
