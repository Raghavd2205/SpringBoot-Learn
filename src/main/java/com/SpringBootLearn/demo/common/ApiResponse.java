package com.SpringBootLearn.demo.common;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private String status;
    private int statusCode;
    private boolean success;
    private T data;
}
