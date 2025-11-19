package com.SpringBootLearn.demo.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private int id;
    private String name;
    private String email;
    private int postCount;
}
