package com.example.AuthDemo.exception;

public class UserForbiddenException extends RuntimeException {
    public UserForbiddenException(String message) {
        super(message);
    }
}
