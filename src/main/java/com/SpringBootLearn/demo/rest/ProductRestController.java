package com.SpringBootLearn.demo.rest;

import com.SpringBootLearn.demo.common.ApiResponse;
import com.SpringBootLearn.demo.entity.Product;
import com.SpringBootLearn.demo.exception.ResourceNotFoundException;
import com.SpringBootLearn.demo.service.ProductService;
import com.SpringBootLearn.demo.utility.ResponseUtil;
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
    public ResponseEntity<ApiResponse<List<Product>>> createProduct(@Valid @RequestBody List<Product> product) {
        return ResponseUtil.success(productService.addProduct(product),201);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProduct() {
        return ResponseUtil.success(productService.getAllProducts(),200);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Integer id) {
        Product result = productService.getProductById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with Id:"+id));
            return ResponseUtil.success(result,200);
    }
    @PutMapping
    public ResponseEntity<ApiResponse<Product>> updateProduct(@Valid @RequestBody Product product) {
        return  ResponseUtil.success(productService.updateProduct(product.getId(), product),200);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseUtil.success("Deleted Successfuly",200);
    }
    @GetMapping("/category/{category}")
    public ResponseEntity< ApiResponse<List<Product>>> getProductByCategory(@PathVariable String category) {
        List<Product> result = productService.findByCategory(category);
            return ResponseUtil.success(result,200);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<List<Product>>> getProductByName(@PathVariable String name) {
        List<Product> result = productService.findByNameContainingIgnoreCase(name);
            return ResponseUtil.success(result,200);

    }
    @GetMapping("/search/{value}")
    public ResponseEntity<ApiResponse<List<Product>>>  searchProduct(@PathVariable String value) {
        List<Product> result = productService.searchProduct(value);
        return ResponseUtil.success(result,200);

    }
}
