package com.SpringBootLearn.demo.rest;

import com.SpringBootLearn.demo.entity.Product;
import com.SpringBootLearn.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        return ResponseEntity.status(201).body(productService.addProduct(product));
    }
    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id) {
        Optional<Product> result = productService.getProductById(id);
        if(result.isPresent()){
            return ResponseEntity.ok(result);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found with id " + id);
        }

    }
    @PutMapping
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product) {
        return ResponseEntity.status(201).body(productService.updateProduct(product.getId(), product));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Deleted Successfuly");
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<?> getProductByCategory(@PathVariable String category) {
        List<Product> result = productService.findByCategory(category);
        if(!result.isEmpty()){
            return ResponseEntity.ok(result);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found with category " + category);
        }

    }
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getProductByName(@PathVariable String name) {
        List<Product> result = productService.findByNameContainingIgnoreCase(name);
        if(!result.isEmpty()){
            return ResponseEntity.ok(result);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found with name " + name);
        }

    }
}
