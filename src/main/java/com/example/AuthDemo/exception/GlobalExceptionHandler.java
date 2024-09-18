package com.example.AuthDemo.exception;

import com.example.AuthDemo.response.GlobalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<GlobalResponse> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(
                new GlobalResponse<>(400, e.getMessage(), null),
                HttpStatus.BAD_REQUEST
        );
    }

}
