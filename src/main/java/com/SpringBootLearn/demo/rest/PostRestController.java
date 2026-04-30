package com.SpringBootLearn.demo.rest;

import com.SpringBootLearn.demo.common.ApiResponse;
import com.SpringBootLearn.demo.dto.PostRequestDTO;
import com.SpringBootLearn.demo.dto.PostResponseDTO;
import com.SpringBootLearn.demo.kafka.PostProducer;
import com.SpringBootLearn.demo.service.PostService;
import com.SpringBootLearn.demo.utility.ResponseUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostRestController {
    public PostRestController(PostProducer postProducer, PostService postService) {
        this.postProducer = postProducer;
        this.postService = postService;
    }

    PostService postService;
    PostProducer postProducer;

    @PostMapping("/{userId}")
    public ResponseEntity<PostResponseDTO> createPost(@PathVariable int userId, @Valid @RequestBody PostRequestDTO post){
        return ResponseEntity.status(201).body(postService.createPost(userId,post));
    }

    @PostMapping("/publish")
    public ResponseEntity<ApiResponse<String>> publish(@RequestBody PostRequestDTO post) throws JsonProcessingException {
        return ResponseUtil.success(postProducer.send(post),201);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllPost(){
        return ResponseEntity.ok(postService.getAllPosts());
    }


    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getAllPost(@PathVariable int id){
        return ResponseEntity.ok(postService.getPostById(id));
    }
}
