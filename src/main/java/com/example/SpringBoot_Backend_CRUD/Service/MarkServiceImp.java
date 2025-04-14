package com.example.SpringBoot_Backend_CRUD.Service;

import com.example.SpringBoot_Backend_CRUD.DTO.MarkDTO;
import com.example.SpringBoot_Backend_CRUD.Entity.Course;
import com.example.SpringBoot_Backend_CRUD.Entity.Mark;
import com.example.SpringBoot_Backend_CRUD.Entity.Student;
import com.example.SpringBoot_Backend_CRUD.Exception.CourseNotFoundException;
import com.example.SpringBoot_Backend_CRUD.Exception.MarkNotFoundException;
import com.example.SpringBoot_Backend_CRUD.Exception.StudentNotFoundException;
import com.example.SpringBoot_Backend_CRUD.Repository.CourseRepository;
import com.example.SpringBoot_Backend_CRUD.Repository.MarkRepository;
import com.example.SpringBoot_Backend_CRUD.Repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MarkServiceImp implements MarkService {
    private MarkRepository markRepository ;
    private StudentRepository studentRepository ;
    private CourseRepository courseRepository ;
    @Autowired
    public MarkServiceImp(MarkRepository markRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.markRepository = markRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public static MarkDTO ConvertToDTO(Mark mark) {
        if (mark == null) return null;

        return new MarkDTO(
                mark.getMarkId(),
                mark.getStudent() != null ? mark.getStudent().getId() : null,
                mark.getCourse() != null ? mark.getCourse().getId() : null,
                mark.getLanHoc(),
                mark.getMark()
        );
    }

    @Override
    public MarkDTO findMark(int id) {
       Mark mark = markRepository.findById(id)
               .orElseThrow(()-> new MarkNotFoundException("Erorr :: Don't find mark by mark id : " + id));
       return ConvertToDTO(mark);
    }

    @Override
    public MarkDTO AddMark(Mark mark) {
        int courseID  = mark.getCourse().getId() ;
        int studentID = mark.getStudent().getId() ;
        Course course = courseRepository.findById(courseID)
                .orElseThrow(()-> new CourseNotFoundException("Error : Don't find course by courseID : " +courseID)) ;
        Student student = studentRepository.findById(studentID)
                .orElseThrow(()-> new StudentNotFoundException("Error : Don't find student by studentID : " +studentID)) ;

        Mark markAdd = markRepository.saveAndFlush(mark) ;
        return ConvertToDTO(markAdd);
    }

    @Override
    public void RemoveMark(int id) {
        Mark mark = markRepository.findById(id)
                .orElseThrow(()-> new MarkNotFoundException("Error : Don't find course by courseID : " +id)) ;
        Student student = mark.getStudent() ;
        student.removeMark(mark);
        Course course = mark.getCourse() ;
        course.removeMark(mark);
        markRepository.deleteById(id);

    }

    @Override
    public MarkDTO UpdateMark(int id, Mark mark) {
        Mark markExist = markRepository.findById(id)
                .orElseThrow(()-> new MarkNotFoundException("Error : Don't find course by courseID : " +id)) ;
        markExist.setMark(mark.getMark());
        markExist.setLanHoc(mark.getLanHoc());
        if(mark.getStudent() != null) {
            Student studentExist = markExist.getStudent() ;
            studentExist.removeMark(markExist);
            studentExist.addMark(mark);
        }
        if(mark.getCourse()!= null) {
            Course courseExist = markExist.getCourse() ;
            courseExist.removeMark(markExist);
            courseExist.addMark(mark);
        }
        Mark markUpdate = markRepository.saveAndFlush(mark) ;
        return ConvertToDTO(markUpdate);
    }

    @Override
    public List<MarkDTO> findAll() {
        List<Mark> marks = markRepository.findAll() ;
        List<MarkDTO>  markDTOS = new ArrayList<>() ;
        marks.forEach(mark -> {
            markDTOS.add(ConvertToDTO(mark)) ;
        });
        return markDTOS ;
    }
}
