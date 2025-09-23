package com.SpringBootLearn.demo.exception;

import com.SpringBootLearn.demo.entity.StudentErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//What is @ControllerAdvice in Spring?
//
//@ControllerAdvice is a specialized Spring annotation used to handle exceptions globally across the whole application (all controllers).
//
//Instead of writing @ExceptionHandler methods in each controller, you write them once inside a @ControllerAdvice class.
//
//Spring will automatically apply it wherever the matching exception is thrown.
@ControllerAdvice
public class StudentRestExceptionHandler {
    
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage((exc.getMessage()));
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<StudentErrorResponse> handleException(Exception exc){
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage((exc.getMessage()));
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
