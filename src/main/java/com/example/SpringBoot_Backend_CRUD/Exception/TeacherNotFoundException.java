package com.example.SpringBoot_Backend_CRUD.Exception;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String message) {
        super(message);
    }
}
