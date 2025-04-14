package com.example.SpringBoot_Backend_CRUD.Service;

import com.example.SpringBoot_Backend_CRUD.Entity.Course;

public interface CourseService {
    public void AddCourse(Course course) ;
    public void findCourse(int id) ;
    public void RemoveCourse(int id) ;
    public void UpdateCourse(int id);
}
