package com.SpringBootLearn.demo.kafka;

import com.SpringBootLearn.demo.common.ApiResponse;
import com.SpringBootLearn.demo.dto.PostRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PostProducer {
    private final KafkaTemplate<String,String> kafkaTemplate;

    public PostProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public String send(PostRequestDTO post) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(post);
        kafkaTemplate.send("posts-topic", json);
        return "Message sent to kafka successfully !!";
    }
}
