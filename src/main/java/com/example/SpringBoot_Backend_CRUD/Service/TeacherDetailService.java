package com.example.SpringBoot_Backend_CRUD.Service;

import com.example.SpringBoot_Backend_CRUD.Entity.Student;
import com.example.SpringBoot_Backend_CRUD.Entity.Teacher;

public interface TeacherService {
    public void AddTeacher(Teacher teacher) ;
    public void findTeacher(int id) ;
    public void RemoveTeacher(int id) ;
    public void UpdateTeacher(int id);
}
