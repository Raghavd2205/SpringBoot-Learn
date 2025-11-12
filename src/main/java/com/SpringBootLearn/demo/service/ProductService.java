package com.SpringBootLearn.demo.service;

import com.SpringBootLearn.demo.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product addProduct(Product product);

    List<Product> getAllProducts();

    Optional<Product> getProductById(Integer id);

    Product updateProduct(Integer id, Product updatedProduct);

    void deleteProduct(Integer id);

    List<Product> findByCategory(String category);
    List<Product> findByNameContainingIgnoreCase(String category);


}
