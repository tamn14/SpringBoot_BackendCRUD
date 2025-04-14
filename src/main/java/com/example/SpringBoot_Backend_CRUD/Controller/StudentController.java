package com.example.SpringBoot_Backend_CRUD.Controller;

import com.example.SpringBoot_Backend_CRUD.DTO.StudentDTO;
import com.example.SpringBoot_Backend_CRUD.Entity.Student;
import com.example.SpringBoot_Backend_CRUD.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService ;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> getAllStudent() {
        return studentService.findAll() ;
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentByID(@PathVariable int id) {
        StudentDTO studentDTO = studentService.findStudent(id) ;
        return ResponseEntity.ok(studentDTO);
    }
    @PostMapping
    public ResponseEntity<StudentDTO> addStudent(@RequestBody  Student student) {
        StudentDTO studentDTO = studentService.AddStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentDTO) ;
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable int id, @RequestBody Student student) {
        StudentDTO studentDTO = studentService.UpdateStudent(student , id) ;
        return ResponseEntity.ok(studentDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        studentService.RemoveStudent(id);
        return ResponseEntity.ok().build();
    }
}
