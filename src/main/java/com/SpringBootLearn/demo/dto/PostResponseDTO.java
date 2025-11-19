package com.SpringBootLearn.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class PostResponseDTO {
    private int id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private int userId;
}
