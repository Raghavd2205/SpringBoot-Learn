package com.SpringBootLearn.demo.dao;
import com.SpringBootLearn.demo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{
    private EntityManager entityManager;
    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee order by Id desc",Employee.class);
        return theQuery.getResultList();
    }

    @Override
    public Employee findEmployeeById(Integer Id) {
      Employee theEmployee = entityManager.find(Employee.class,Id);
        return theEmployee;
    }

    @Override
    public void addEmployee(Employee theEmployee) {
    entityManager.merge(theEmployee);
    }
}
