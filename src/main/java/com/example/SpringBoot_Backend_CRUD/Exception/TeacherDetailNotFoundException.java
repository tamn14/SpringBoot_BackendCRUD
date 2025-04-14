package com.example.SpringBoot_Backend_CRUD.Exception;

public class TeacherDetailNotFoundException extends RuntimeException {
    public TeacherDetailNotFoundException(String message) {
        super(message);
    }
}
