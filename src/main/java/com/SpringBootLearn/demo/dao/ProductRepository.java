package com.SpringBootLearn.demo.dao;

import com.SpringBootLearn.demo.entity.Employee;
import com.SpringBootLearn.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory(String category);
    List<Product> findByNameContainingIgnoreCase(String name);
}
