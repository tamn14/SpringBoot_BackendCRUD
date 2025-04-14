package com.example.SpringBoot_Backend_CRUD.Service;

import com.example.SpringBoot_Backend_CRUD.DTO.TeacherDetailDTO;
import com.example.SpringBoot_Backend_CRUD.Entity.Teacher;
import com.example.SpringBoot_Backend_CRUD.Entity.TeacherDetail;

import java.util.List;

public interface TeacherDetailService {
    public TeacherDetailDTO AddTeacherDetail(TeacherDetail teacherDetail) ;
    public TeacherDetailDTO findTeacherDetail(int id) ;
    public void RemoveTeacherDetail(int id) ;
    public TeacherDetailDTO UpdateTeacherDetail(int id , TeacherDetail teacherDetail);
    public List<TeacherDetailDTO> findAll() ;
}
