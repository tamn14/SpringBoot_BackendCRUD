package com.example.SpringBoot_Backend_CRUD.Service;

import com.example.SpringBoot_Backend_CRUD.DTO.StudentDTO;
import com.example.SpringBoot_Backend_CRUD.DTO.TeacherDTO;
import com.example.SpringBoot_Backend_CRUD.DTO.TeacherDetailDTO;
import com.example.SpringBoot_Backend_CRUD.Entity.Course;
import com.example.SpringBoot_Backend_CRUD.Entity.Student;
import com.example.SpringBoot_Backend_CRUD.Entity.Teacher;
import com.example.SpringBoot_Backend_CRUD.Entity.TeacherDetail;
import com.example.SpringBoot_Backend_CRUD.Exception.TeacherDetailNotFoundException;
import com.example.SpringBoot_Backend_CRUD.Repository.TeacherDetailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeacherDetailServiceImp implements TeacherDetailService {
    private TeacherDetailRepository teacherDetailRepository ;
    @Autowired
    public TeacherDetailServiceImp(TeacherDetailRepository teacherDetailRepository) {
        this.teacherDetailRepository = teacherDetailRepository;
    }
    public static TeacherDetailDTO convertToDTO(TeacherDetail teacherDetail) {

        return new TeacherDetailDTO(
                teacherDetail.getId() ,
                teacherDetail.getAddress(),
                teacherDetail.getSalary(),
                teacherDetail.getDepartment()
        );
    }


    @Override
    public TeacherDetailDTO AddTeacherDetail(TeacherDetail teacherDetail) {
        Teacher teacher = teacherDetail.getTeacher() ;
        if(teacher != null) {
            teacherDetail.AddTeacher(teacher);
        }
        TeacherDetail teacherDetail1 = teacherDetailRepository.saveAndFlush(teacherDetail);
        return convertToDTO(teacherDetail) ;



    }

    @Override
    public TeacherDetailDTO findTeacherDetail(int id) {
        TeacherDetail teacherDetail = teacherDetailRepository.findById(id)
                .orElseThrow(()-> new TeacherDetailNotFoundException("Error :: Don't find teacher detail by id : " + id)) ;
        return convertToDTO(teacherDetail);
    }

    @Override
    public void RemoveTeacherDetail(int id) {
        TeacherDetail teacherDetail = teacherDetailRepository.findById(id)
                .orElseThrow(()-> new TeacherDetailNotFoundException("Error :: Don't find teacher detail by id : " + id)) ;
        if(teacherDetail.getTeacher() != null) {
            teacherDetail.getTeacher().AddDetail(teacherDetail);
        }
        teacherDetailRepository.deleteById(id);

    }

    @Override
    public TeacherDetailDTO UpdateTeacherDetail(int id , TeacherDetail teacherDetail) {
        TeacherDetail teacherDetailExist = teacherDetailRepository.findById(id)
                .orElseThrow(()-> new TeacherDetailNotFoundException("Error :: Don't find teacher detail by id : " + id)) ;

        teacherDetailExist.setSalary(teacherDetail.getSalary());
        teacherDetailExist.setAddress(teacherDetail.getAddress());
        teacherDetailExist.setDepartment(teacherDetail.getDepartment());

        TeacherDetail teacherDetailUpdate = teacherDetailRepository.saveAndFlush(teacherDetail);
        return convertToDTO(teacherDetail);

    }

    @Override
    public List<TeacherDetailDTO> findAll() {
        List<TeacherDetail> teacherDetails = teacherDetailRepository.findAll();
        List<TeacherDetailDTO> teacherDetailDTOS = new ArrayList<>() ;
        teacherDetails.forEach(teacherDetail -> {
           teacherDetailDTOS.add(convertToDTO(teacherDetail));
        });
        return teacherDetailDTOS ;
    }

}
