package com.SpringBootLearn.demo.exception;

import com.SpringBootLearn.demo.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

//@RestControllerAdvice lets you handle all API errors globally and return clean JSON responses
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ✅ 404 - Resource Not Found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFound(ResourceNotFoundException ex) {
        return buildResponse(ex.getMessage(), 404, "NOT_FOUND");
    }

    // ✅ 401 - Unauthorized
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<Object>> handleUnauthorized(UnauthorizedException ex) {
        return buildResponse(ex.getMessage(), 401, "UNAUTHORIZED");
    }

    // ✅ 400 - Validation Error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidation(MethodArgumentNotValidException ex) {

        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return buildResponse(errorMessage, 400, "BAD_REQUEST");
    }

    // ✅ Generic Runtime Exception
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handleRuntime(RuntimeException ex) {
        return buildResponse(ex.getMessage(), 500, "INTERNAL_SERVER_ERROR");
    }

    // ✅ Catch All
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception ex) {
        return buildResponse("Something went wrong", 500, "INTERNAL_SERVER_ERROR");
    }

    //  Common builder method
    private ResponseEntity<ApiResponse<Object>> buildResponse(String message, int code, String status) {
        return ResponseEntity.status(code).body(
                ApiResponse.builder()
                        .status(status)
                        .statusCode(code)
                        .success(false)
                        .data(message)
                        .build()
        );
    }
}
