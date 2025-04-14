package com.example.SpringBoot_Backend_CRUD.Exception;

import com.example.SpringBoot_Backend_CRUD.ResponseError.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ResponseError> handleStudentNotFoundException(StudentNotFoundException ex) {
        ResponseError responseError = new ResponseError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);
    }
    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ResponseError> handleCourseNotFoundException(StudentNotFoundException ex) {
        ResponseError responseError = new ResponseError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);
    }
    @ExceptionHandler(TeacherNotFoundException.class)
    public ResponseEntity<ResponseError> handleTeacherNotFoundException(StudentNotFoundException ex) {
        ResponseError responseError = new ResponseError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);
    }

    @ExceptionHandler(TeacherDetailNotFoundException.class)
    public ResponseEntity<ResponseError> handleTeacherDetailNotFoundException(StudentNotFoundException ex) {
        ResponseError responseError = new ResponseError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);
    }

    @ExceptionHandler(MarkNotFoundException.class)
    public ResponseEntity<ResponseError> handleMarkDetailNotFoundException(StudentNotFoundException ex) {
        ResponseError responseError = new ResponseError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleAllExceptions(Exception ex) {
        ResponseError responseError = new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
    }
}
