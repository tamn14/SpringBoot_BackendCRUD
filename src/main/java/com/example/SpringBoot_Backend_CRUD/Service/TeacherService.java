package com.example.SpringBoot_Backend_CRUD.Service;

import com.example.SpringBoot_Backend_CRUD.Entity.Course;
import com.example.SpringBoot_Backend_CRUD.Entity.Student;

public interface StudentService {
    public void AddStudent(Student student) ;
    public void findStudent(int id) ;
    public void RemoveStudent(int id) ;
    public void UpdateStudent(int id);
}
