package com.example.SpringBoot_Backend_CRUD.Service;

import com.example.SpringBoot_Backend_CRUD.DTO.CourseDTO;
import com.example.SpringBoot_Backend_CRUD.Entity.Course;
import com.example.SpringBoot_Backend_CRUD.Entity.Student;
import com.example.SpringBoot_Backend_CRUD.Entity.Teacher;

import java.util.List;

public interface CourseService {
    public CourseDTO AddCourse(Course course) ;
    public CourseDTO findCourse(int id) ;
    public void RemoveCourse(int id) ;
    public CourseDTO UpdateCourse(Course course , int id);
    public List<CourseDTO> GetAllCourse() ;

}
