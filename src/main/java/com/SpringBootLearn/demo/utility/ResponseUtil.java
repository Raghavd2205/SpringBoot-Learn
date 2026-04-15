package com.SpringBootLearn.demo.utility;

import com.SpringBootLearn.demo.common.ApiResponse;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    private static String status = "OK";

    public static <T> ResponseEntity<ApiResponse<T>> success(T data, Integer code) {
        if(code == 201){
            status = "CREATED";
        }
        return ResponseEntity.ok(
                ApiResponse.<T>builder()
                        .status(status)
                        .statusCode(code)
                        .success(true)
                        .data(data)
                        .build()
        );
    }
}
