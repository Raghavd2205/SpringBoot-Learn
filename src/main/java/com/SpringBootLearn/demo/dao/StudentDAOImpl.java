package com.SpringBootLearn.demo.dao;

import com.SpringBootLearn.demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public Student findById(Integer Id) {
        return entityManager.find(Student.class,Id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student order by Id desc",Student.class);
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student where lastName=:$1 order by Id desc",Student.class);
        theQuery.setParameter("$1",theLastName);
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Student theStudent){
//        merge() = smart → reattaches detached entities.
//        remove() = strict → works only on managed entities, thats why you are saying bellow line of merge.
        Student managed = entityManager.merge(theStudent); // reattach
        entityManager.remove(managed);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int nom= entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return nom;
    }
}
