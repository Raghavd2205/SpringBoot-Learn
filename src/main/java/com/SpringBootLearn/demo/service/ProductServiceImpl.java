package com.SpringBootLearn.demo.service;

import com.SpringBootLearn.demo.dao.ProductRepository;
import com.SpringBootLearn.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product){
       return this.productRepository.save(product);
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
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
        existing.setName(updatedProduct.getName());
        existing.setCategory(updatedProduct.getCategory());
        existing.setStockQuantity(updatedProduct.getStockQuantity());
        existing.setPrice(updatedProduct.getPrice());
        return productRepository.save(existing);

    }

    @Override
    public void deleteProduct(Integer id) {
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
}
