package com.SpringBootLearn.demo.dto;

import com.SpringBootLearn.demo.entity.User;
import lombok.Data;

@Data
public class PostRequestDTO {
    public Integer id;
    private String title;
    private String content;


}
