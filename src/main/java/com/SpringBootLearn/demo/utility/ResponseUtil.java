package com.SpringBootLearn.demo.utility;

import com.SpringBootLearn.demo.common.ApiResponse;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    private static String status = "OK";

    public static <T> ResponseEntity<ApiResponse<T>> success(T data, Integer code) {
        if(code == 201){
            status = "CREATED";
        }
        else{
            status = "OK";
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
    public static <T> ResponseEntity<ApiResponse<T>> Failure(T data, Integer code) {
        switch (code) {
            case 400:
                status ="Bad Request";
                break;

            case 401:
                status ="Unauthorized";
                // optional: redirect to login
                break;

            case 403:
                status = "Access Denied";
                break;

            case 404:
                status ="API Not Found";
                break;

            case 500:
                status = "Server Error. Please try again later";
                break;

            default:
                status ="Error";
        }

        return ResponseEntity.status(code).body(
                ApiResponse.<T>builder()
                        .status(status)
                        .statusCode(code)
                        .success(false)
                        .data(data)
                        .build()
        );
    }
}
