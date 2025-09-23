package com.SpringBootLearn.demo.service;

import com.SpringBootLearn.demo.dao.EmployeeDAO;
import com.SpringBootLearn.demo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeDAO employeeDao;
    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> theEmployees = employeeDao.findAll();
        return theEmployees;
    }

    @Override
    public Employee findEmployeeById(Integer Id) {
        return employeeDao.findEmployeeById(Id);
    }

    @Override
    @Transactional
    public void addEmployee(Employee theEmployee) {
        employeeDao.addEmployee(theEmployee);
    }
}
