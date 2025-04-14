package com.example.SpringBoot_Backend_CRUD.Service;

import com.example.SpringBoot_Backend_CRUD.DTO.CourseDTO;
import com.example.SpringBoot_Backend_CRUD.DTO.StudentDTO;
import com.example.SpringBoot_Backend_CRUD.Entity.Course;
import com.example.SpringBoot_Backend_CRUD.Entity.Student;
import com.example.SpringBoot_Backend_CRUD.Entity.Teacher;
import com.example.SpringBoot_Backend_CRUD.Exception.CourseNotFoundException;
import com.example.SpringBoot_Backend_CRUD.Exception.StudentNotFoundException;
import com.example.SpringBoot_Backend_CRUD.Repository.CourseRepository;
import com.example.SpringBoot_Backend_CRUD.Repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImp implements StudentService{
    private StudentRepository studentRepository ;
    private CourseRepository courseRepository ;
    @Autowired
    public StudentServiceImp(StudentRepository studentRepository , CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository ;
    }

    public static StudentDTO convertToDTO(Student student) {
        List<Integer> courseIds = student.getCourses()
                .stream()
                .map(Course::getId)
                .collect(Collectors.toList());

        return new StudentDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getBirthDay(),
                student.isEnrolled(),
                courseIds
        );
    }

    @Override
    public StudentDTO AddStudent(Student student) {
        List<Course> courses = student.getCourses();
        List<Course> validCourses = new ArrayList<>();

        if (courses != null && !courses.isEmpty()) {
            for (Course course : courses) {
                int courseExistId = course.getId();
                Course courseExist = courseRepository.findById(courseExistId)
                        .orElseThrow(() -> new CourseNotFoundException("Error :: Don't find course by courseID : " + courseExistId));
                validCourses.add(courseExist);
            }
        }


        student.getCourses().clear();
        validCourses.forEach(student::AddCourse);

        Student studentAdd = studentRepository.saveAndFlush(student);
        return convertToDTO(studentAdd);
    }
    @Override
    public StudentDTO findStudent(int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()-> new StudentNotFoundException("Error : Don't find student by studentID : " + id)) ;
        return convertToDTO(student) ;
    }

    @Override
    public void RemoveStudent(int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()-> new StudentNotFoundException("Error :: Don't find student by studentID" + id)) ;
        List<Course> courses = new ArrayList<>(student.getCourses()) ;
        // Delete relationship between course and student (n-n)
        courses.forEach(course -> {
            student.DeleteCourse(course);
        });
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDTO UpdateStudent(Student student, int id) {
        Student studentExist = studentRepository.findById(id)
                .orElseThrow(()-> new StudentNotFoundException("Error :: Don't find student by studentID" + id)) ;
        studentExist.setFirstName(student.getFirstName());
        studentExist.setLastName(student.getLastName());
        studentExist.setBirthDay(student.getBirthDay());
        studentExist.setEnrolled(student.isEnrolled());
        // get list course need to update
        List<Course> listCoursesUpdate = student.getCourses();
        List<Course> listCoursesExistsInStudent = new ArrayList<>(studentExist.getCourses());
        // Update list course
        if(listCoursesUpdate.size() > 0) {
            if(listCoursesExistsInStudent.size() > 0) {
                listCoursesExistsInStudent.forEach(course -> {
                    studentExist.DeleteCourse(course);
                });
                listCoursesUpdate.forEach(course -> {
                    int courseExistId = course.getId();
                    Course CourseExist = courseRepository.findById(courseExistId)
                            .orElseThrow(()-> new CourseNotFoundException("Error :: Don't find course by courseID : " + courseExistId)) ;
                    studentExist.AddCourse(course);
                });
            }
            else {
                listCoursesUpdate.forEach(course -> {
                    int courseExistId = course.getId();
                    Course CourseExist = courseRepository.findById(courseExistId)
                            .orElseThrow(()-> new CourseNotFoundException("Error :: Don't find course by courseID : " + courseExistId)) ;
                    studentExist.AddCourse(course);
                });
            }
        }

        Student studentUpdate = studentRepository.saveAndFlush(studentExist) ;
        return convertToDTO(studentUpdate);

    }


    @Override
    public List<StudentDTO> findAll() {
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOS = new ArrayList<>() ;
        students.forEach(student ->  {
            studentDTOS.add(convertToDTO(student));
        });
        return studentDTOS ;

    }
}
