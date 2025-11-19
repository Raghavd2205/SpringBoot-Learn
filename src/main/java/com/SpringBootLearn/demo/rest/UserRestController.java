package com.SpringBootLearn.demo.rest;

import com.SpringBootLearn.demo.dto.UserRequestDTO;
import com.SpringBootLearn.demo.dto.UserResponseDTO;
import com.SpringBootLearn.demo.dto.PostResponseDTO;
import com.SpringBootLearn.demo.service.UserService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    // ---------------- CREATE USER ----------------
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO dto) {
        UserResponseDTO created = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ---------------- GET ALL USERS ----------------
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // ---------------- GET USER BY ID ----------------
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with id " + id);
        }
    }

    // ---------------- UPDATE USER ----------------
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUserById(@PathVariable Integer id,
                                                          @Valid @RequestBody UserRequestDTO dto) {
        UserResponseDTO updated = userService.updateUser(id, dto);
        return ResponseEntity.ok(updated);
    }

    // ---------------- DELETE USER ----------------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Deleted Successfully");
    }

    // ---------------- GET USER POSTS ----------------
    @GetMapping("/{userId}/posts")
    public ResponseEntity<?> getPostsByUserId(@PathVariable Integer userId) {
        try {
            List<PostResponseDTO> posts = userService.findPostsById(userId);
            return posts.isEmpty()
                    ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("No posts found for user " + userId)
                    : ResponseEntity.ok(posts);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with id " + userId);
        }
    }
}
