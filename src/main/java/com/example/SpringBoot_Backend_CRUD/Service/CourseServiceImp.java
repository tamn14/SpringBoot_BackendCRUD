package com.example.SpringBoot_Backend_CRUD.Service;

import com.example.SpringBoot_Backend_CRUD.DTO.CourseDTO;
import com.example.SpringBoot_Backend_CRUD.Entity.Course;
import com.example.SpringBoot_Backend_CRUD.Entity.Student;
import com.example.SpringBoot_Backend_CRUD.Entity.Teacher;
import com.example.SpringBoot_Backend_CRUD.Exception.CourseNotFoundException;
import com.example.SpringBoot_Backend_CRUD.Exception.TeacherNotFoundException;
import com.example.SpringBoot_Backend_CRUD.Repository.CourseRepository;
import com.example.SpringBoot_Backend_CRUD.Repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CourseServiceImp implements CourseService {
    private CourseRepository courseRepository ;
    private TeacherRepository teacherRepository ;

    @Autowired
    public CourseServiceImp(CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;

    }

    public CourseDTO convertToDTO(Course course) {
        Teacher teacher = course.getTeacher(); // Get teacher from course
        return new CourseDTO(
                course.getId(),
                course.getName(),
                course.getBegin(),
                course.getEnd(),
                teacher != null ? teacher.getId() : 0
        );
    }

    @Override
    public CourseDTO AddCourse(Course course) {
        if(course.getTeacher()!= null) {
            // get teacher id from course
            int id = course.getTeacher().getId() ;
            Teacher teacher = teacherRepository.findById(id)
                    .orElseThrow(()-> new TeacherNotFoundException("Error : Don't find teacher by teacherID : " + id)) ;
            teacher.AddCourse(course);
        }
        Course courseAdd =  courseRepository.saveAndFlush(course) ;
        return convertToDTO(courseAdd);
    }

    @Override
    public CourseDTO findCourse(int id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Error: Course not found with ID " + id));
        return convertToDTO(course);
    }

    @Override
    public void RemoveCourse(int id) {
        // check course is exist
         Course course = courseRepository.findById(id)
                 .orElseThrow(()->new CourseNotFoundException("Error: Don't find course by courseID : " + id)) ;
         // get list student
         List<Student> students = course.getStudents() ;
         // Delete relationship between course and student
         students.forEach(student -> {
             student.DeleteCourse(course);
         });
         // delete course
         courseRepository.deleteById(id);
    }

    @Override
    public CourseDTO UpdateCourse(Course course ,  int id ) {
        // check course is exist
        Course courseExsist = courseRepository.findById(id)
                .orElseThrow(()-> new CourseNotFoundException("Error:: Don't find course by courseID : " + id)) ;
        // set valeau update
        courseExsist.setName(course.getName());
        courseExsist.setBegin(course.getBegin());
        courseExsist.setEnd(course.getEnd());
        Teacher teacher = course.getTeacher() ;
        // update teacher in course ( teacher and course have relationship 1-n)
        if(course.getTeacher() != null && !(course.getTeacher().equals(courseExsist.getTeacher()))){
            int teacherID = course.getTeacher().getId();
            Teacher teacherExist = teacherRepository.findById(teacherID)
                    .orElseThrow(()-> new TeacherNotFoundException("Error :: Don't find teacher by teacherID : " + teacherID)) ;
            if (courseExsist.getTeacher() != null) {
                courseExsist.getTeacher().DeleteCourse(courseExsist);
            }
            teacherExist.AddCourse(courseExsist);
        }
        Course updateCourse = courseRepository.saveAndFlush(courseExsist);
        return convertToDTO(updateCourse);
    }

    @Override
    public List<CourseDTO> GetAllCourse() {
        List<Course> course =  courseRepository.findAll();
        List<CourseDTO> courseDTOS = new ArrayList<CourseDTO>() ;
        course.forEach(course1 -> {
            CourseDTO courseDTO = convertToDTO(course1);
            courseDTOS.add(courseDTO) ;
        });
        return courseDTOS;
    }

}
