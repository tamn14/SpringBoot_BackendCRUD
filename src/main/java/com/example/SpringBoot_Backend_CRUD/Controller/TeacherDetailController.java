package com.example.SpringBoot_Backend_CRUD.Controller;

import com.example.SpringBoot_Backend_CRUD.DTO.TeacherDetailDTO;
import com.example.SpringBoot_Backend_CRUD.Entity.TeacherDetail;
import com.example.SpringBoot_Backend_CRUD.Service.TeacherDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detail")
public class TeacherDetailController {
    private TeacherDetailService teacherDetailService ;
    @Autowired
    public TeacherDetailController(TeacherDetailService teacherDetailService) {
        this.teacherDetailService = teacherDetailService;
    }
    @GetMapping
    public List<TeacherDetailDTO> getAll() {
        return teacherDetailService.findAll() ;

    }
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDetailDTO> teacherDetailResponseEntity(@PathVariable int id ) {
        TeacherDetailDTO teacherDetailDTO = teacherDetailService.findTeacherDetail(id) ;
        return ResponseEntity.ok(teacherDetailDTO) ;
    }
    @PostMapping
    public ResponseEntity<TeacherDetailDTO> addTeacherDetail(@RequestBody TeacherDetail teacherDetail) {
        TeacherDetailDTO teacherDetailDTO = teacherDetailService.AddTeacherDetail(teacherDetail) ;
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherDetailDTO);
    }
    @PutMapping("{id}")
    public ResponseEntity<TeacherDetailDTO> updateTeacher(@PathVariable int id, @RequestBody TeacherDetail teacherDetail) {
        TeacherDetailDTO teacherDetailDTO = teacherDetailService.UpdateTeacherDetail(id, teacherDetail) ;
        return ResponseEntity.ok(teacherDetailDTO);
    }
    @DeleteMapping("/id")
    public ResponseEntity<Void> deletTeacher(@PathVariable int id) {
       teacherDetailService.RemoveTeacherDetail(id);
       return ResponseEntity.ok().build() ;

    }
}
