package com.example.SpringBoot_Backend_CRUD.Service;

import com.example.SpringBoot_Backend_CRUD.DTO.TeacherDTO;
import com.example.SpringBoot_Backend_CRUD.Entity.Course;
import com.example.SpringBoot_Backend_CRUD.Entity.Student;
import com.example.SpringBoot_Backend_CRUD.Entity.Teacher;
import com.example.SpringBoot_Backend_CRUD.Entity.TeacherDetail;

import java.util.List;

public interface TeacherService {
    public TeacherDTO AddTeacher(Teacher teacher) ;
    public List<TeacherDTO> GetAllteachers() ;
    public TeacherDTO findByID(int id) ;
    public void RemoveTeacher(int id) ;
    public TeacherDTO UpdateTeacher(Teacher teacher , int id);

}
