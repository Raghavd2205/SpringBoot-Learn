package com.SpringBootLearn.demo.dto;

import com.SpringBootLearn.demo.entity.User;
import lombok.Data;

@Data
public class PostRequestDTO {
    private String title;
    private String content;


}
