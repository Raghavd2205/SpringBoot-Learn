package com.SpringBootLearn.demo.dao;

import com.SpringBootLearn.demo.entity.Employee;
import com.SpringBootLearn.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory(String category);
    List<Product> findByNameContainingIgnoreCase(String name);
    @Query("""
    SELECT p FROM Product p
    WHERE LOWER(p.category) LIKE LOWER(CONCAT('%', :value, '%')) OR
    LOWER(p.name) LIKE LOWER(CONCAT('%', :value, '%')) 
""")
    List<Product> searchProducts(String value);
}
