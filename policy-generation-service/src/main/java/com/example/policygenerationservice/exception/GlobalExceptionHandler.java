package com.example.policygenerationservice.exception;

public class GlobalExceptionHandler extends RuntimeException {
    public GlobalExceptionHandler(String message) {
        super(message);
    }
}
