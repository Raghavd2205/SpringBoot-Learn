package com.SpringBootLearn.demo.dao;

import com.SpringBootLearn.demo.entity.Mangment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="managements")
public interface MangmentRepository extends JpaRepository<Mangment, Integer> {

}

