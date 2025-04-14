package com.example.spring81.Exception;

import com.example.spring81.ResponseError.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.Exception;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ResponseError> handleStudentNotFoundException(StudentNotFoundException ex) {
        ResponseError responseError = new ResponseError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleAllExceptions(Exception ex) {
        ResponseError responseError = new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
    }
}
