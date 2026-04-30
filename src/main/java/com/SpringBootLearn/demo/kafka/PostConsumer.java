package com.SpringBootLearn.demo.kafka;

import com.SpringBootLearn.demo.dto.PostRequestDTO;
import com.SpringBootLearn.demo.dto.PostResponseDTO;
import com.SpringBootLearn.demo.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class PostConsumer {
    private final PostService postService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PostConsumer(PostService postService) {
        this.postService = postService;
    }

    @PostConstruct
    public void init() {
        System.out.println("🔥 Kafka Consumer Loaded");
    }

    @KafkaListener(topics="posts-topic", groupId="post-group-1")
    public void consume(String message){
        try {
            System.out.println("Received: " + message);
            // 🔥 Convert JSON → Post
            PostRequestDTO post = objectMapper.readValue(message, PostRequestDTO.class);
            Integer id = post.id;
            // Save using existing logic (flagging included)
            PostResponseDTO response = postService.createPost(id,post);
            System.out.println("Sent: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
