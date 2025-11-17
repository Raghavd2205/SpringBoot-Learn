package com.SpringBootLearn.demo.rest;

import com.SpringBootLearn.demo.entity.Post;
import com.SpringBootLearn.demo.entity.Product;
import com.SpringBootLearn.demo.entity.User;
import com.SpringBootLearn.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}/posts")
    public ResponseEntity<?> getPostsByUserId(@PathVariable Integer userId) {
        List<Post> result = userService.findPostsById(userId);
        if(result.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found with id " + userId);
        
        }
        else{
            return ResponseEntity.ok(result);
        }

    }
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        return ResponseEntity.status(201).body(userService.createUser(user));
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id){
        User result = userService.getUserById(id);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with id " + id);
        }

    }
    @PutMapping("/{id}")
    
}
