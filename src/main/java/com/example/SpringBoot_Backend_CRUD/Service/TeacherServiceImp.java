package com.example.SpringBoot_Backend_CRUD.Service;

import com.example.SpringBoot_Backend_CRUD.DTO.CourseDTO;
import com.example.SpringBoot_Backend_CRUD.DTO.TeacherDTO;
import com.example.SpringBoot_Backend_CRUD.DTO.TeacherDetailDTO;
import com.example.SpringBoot_Backend_CRUD.Entity.Course;
import com.example.SpringBoot_Backend_CRUD.Entity.Teacher;
import com.example.SpringBoot_Backend_CRUD.Entity.TeacherDetail;
import com.example.SpringBoot_Backend_CRUD.Exception.CourseNotFoundException;
import com.example.SpringBoot_Backend_CRUD.Exception.TeacherNotFoundException;
import com.example.SpringBoot_Backend_CRUD.Repository.CourseRepository;
import com.example.SpringBoot_Backend_CRUD.Repository.TeacherDetailRepository;
import com.example.SpringBoot_Backend_CRUD.Repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImp implements TeacherService{
   private TeacherRepository teacherRepository ;
    @Autowired
    public TeacherServiceImp(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public TeacherDTO convertToDTO(Teacher teacher) {
        TeacherDetailDTO detailDTO = new TeacherDetailDTO();
        if (teacher.getTeacherDetail() != null) {
            detailDTO.setId(teacher.getTeacherDetail().getId());
            detailDTO.setSalary(teacher.getTeacherDetail().getSalary());
            detailDTO.setAddress(teacher.getTeacherDetail().getAddress());
            detailDTO.setDepartment(teacher.getTeacherDetail().getDepartment());
        }
        return new TeacherDTO(
                teacher.getId(),
                teacher.getLastName(),
                teacher.getFirstName(),
                teacher.getEmail(),
                detailDTO
        );
    }

    @Override
    public TeacherDTO AddTeacher(Teacher teacher) {
        TeacherDetail teacherDetail = teacher.getTeacherDetail();
        if(teacherDetail != null) {
            teacher.AddDetail(teacherDetail);
        }
        Teacher teacherAdd = teacherRepository.saveAndFlush(teacher) ;
        return convertToDTO(teacherAdd);
    }

    @Override
    public List<TeacherDTO> GetAllteachers() {
        List<Teacher> teachers = teacherRepository.findAll() ;
        List<TeacherDTO> teacherDTOS = new ArrayList<>();
        teachers.forEach(teacher -> {
            teacherDTOS.add(convertToDTO(teacher)) ;
        });
        return teacherDTOS ;

    }

    @Override
    public TeacherDTO findByID(int id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(()-> new TeacherNotFoundException("Error:: Don't find teacher by teacherID: " + id)) ;
        return convertToDTO(teacher);
    }

    @Override
    public void RemoveTeacher(int id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(()-> new TeacherNotFoundException("Error :: Don't find teacher by teacherID: " + id)) ;
        teacherRepository.deleteById(id);

    }

    @Override
    public TeacherDTO UpdateTeacher(Teacher teacher, int id) {
        Teacher teacherExist = teacherRepository.findById(id)
                .orElseThrow(()-> new TeacherNotFoundException("Error :: Don't find teacher by teacherID: " + id)) ;
        teacherExist.setLastName(teacher.getLastName());
        teacherExist.setFirstName(teacher.getFirstName());
        teacherExist.setEmail(teacher.getEmail());
        TeacherDetail teacherDetail = teacher.getTeacherDetail() ;

        TeacherDetail teacherDetailUpdate = new TeacherDetail();
        teacherDetailUpdate.setAddress(teacher.getTeacherDetail().getAddress());
        teacherDetailUpdate.setSalary(teacher.getTeacherDetail().getSalary());
        teacherDetailUpdate.setDepartment(teacher.getTeacherDetail().getDepartment());

        teacherExist.AddDetail(teacherDetailUpdate);
        Teacher teacherUpdate = teacherRepository.saveAndFlush(teacherExist) ;
        return convertToDTO(teacherUpdate);
    }
}
