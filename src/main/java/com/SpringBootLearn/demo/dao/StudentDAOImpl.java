package com.SpringBootLearn.demo.dao;

import com.SpringBootLearn.demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// repository annotation supports component scaning, tranbslate jdbc exception, spclzd annotation for repo
@Repository
public class StudentDAOImpl implements StudentDAO{
    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manger using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implment save method
    @Override
    // add transactional since we are performing an update
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }
}
