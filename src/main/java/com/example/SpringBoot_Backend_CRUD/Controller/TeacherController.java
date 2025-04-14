package com.example.SpringBoot_Backend_CRUD.Controller;

import com.example.SpringBoot_Backend_CRUD.DTO.TeacherDTO;
import com.example.SpringBoot_Backend_CRUD.Entity.Course;
import com.example.SpringBoot_Backend_CRUD.Entity.Teacher;
import com.example.SpringBoot_Backend_CRUD.Entity.TeacherDetail;
import com.example.SpringBoot_Backend_CRUD.Exception.CourseNotFoundException;
import com.example.SpringBoot_Backend_CRUD.Exception.TeacherNotFoundException;
import com.example.SpringBoot_Backend_CRUD.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private TeacherService teacherService ;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;

    }

    @GetMapping("/listAll")
    public List<TeacherDTO> getAllTeacher() {

        return teacherService.GetAllteachers() ;
    }
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable("id") int id) {
        TeacherDTO teacher = teacherService.findByID(id) ;
        return ResponseEntity.ok(teacher) ;
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> addTeacher(@RequestBody Teacher teacher) {
        teacher.setId(0); // tranh truong hop id da ton tai
        TeacherDTO teacherDTO = teacherService.AddTeacher(teacher) ;
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherDTO) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable int id , @RequestBody Teacher teacher) {
       TeacherDTO teacherDTO = teacherService.UpdateTeacher(teacher , id) ;
       return ResponseEntity.ok(teacherDTO) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher (@PathVariable int id) {
        teacherService.RemoveTeacher(id);
        return ResponseEntity.ok().build();
    }



}





