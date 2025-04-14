package com.example.SpringBoot_Backend_CRUD.Exception;

public class MarkNotFoundException extends RuntimeException {
    public MarkNotFoundException(String message) {
        super(message);
    }
}
