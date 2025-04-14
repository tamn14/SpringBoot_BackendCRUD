package com.example.SpringBoot_Backend_CRUD.Service;

import com.example.SpringBoot_Backend_CRUD.DTO.StudentDTO;
import com.example.SpringBoot_Backend_CRUD.Entity.Course;
import com.example.SpringBoot_Backend_CRUD.Entity.Student;

import java.util.List;

public interface StudentService {
    public StudentDTO AddStudent(Student student) ;
    public StudentDTO findStudent(int id) ;
    public void RemoveStudent(int id) ;
    public StudentDTO UpdateStudent(Student student , int id);
    public List<StudentDTO> findAll() ;
}
