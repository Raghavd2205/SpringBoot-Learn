package com.SpringBootLearn.demo.service;

import com.SpringBootLearn.demo.dao.ProductRepository;
import com.SpringBootLearn.demo.entity.Product;
import com.SpringBootLearn.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> addProduct(List<Product> product){
       return this.productRepository.saveAll(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Product updateProduct(Integer id, Product updatedProduct) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with Id " + id));
        existing.setName(updatedProduct.getName());
        existing.setCategory(updatedProduct.getCategory());
        existing.setStockQuantity(updatedProduct.getStockQuantity());
        existing.setPrice(updatedProduct.getPrice());
        return productRepository.save(existing);

    }

    @Override
    public void deleteProduct(Integer id) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with Id " + id));
        this.productRepository.deleteById(id);
    }

    @Override
    public List<Product> findByCategory(String category) {
        return this.productRepository.findByCategory(category);
    }
    @Override
    public List<Product> findByNameContainingIgnoreCase(String category) {
        return this.productRepository.findByNameContainingIgnoreCase(category);
    }

    @Override
    public List<Product> searchProduct(String value) {
        return this.productRepository.searchProducts(value);
    }
}
