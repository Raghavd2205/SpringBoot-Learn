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
    public Employee addEmployee(Employee theEmployee) {
    return entityManager.merge(theEmployee);
    }

    @Override
    public void delete(Employee theEmployee) {
        Employee Employee = entityManager.merge(theEmployee);
        entityManager.remove(Employee);
    }
}
