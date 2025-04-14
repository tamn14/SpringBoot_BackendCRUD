package com.example.SpringBoot_Backend_CRUD.Controller;

import com.example.SpringBoot_Backend_CRUD.DTO.CourseDTO;
import com.example.SpringBoot_Backend_CRUD.Entity.Course;
import com.example.SpringBoot_Backend_CRUD.Entity.Teacher;
import com.example.SpringBoot_Backend_CRUD.Repository.CourseRepository;
import com.example.SpringBoot_Backend_CRUD.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    private CourseService courseService ;
    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseByID(@PathVariable int id) {
        CourseDTO courseDTO = courseService.findCourse(id) ;
        return ResponseEntity.ok(courseDTO);
    }

    @GetMapping
    public List<CourseDTO> getAllCourse() {
        return courseService.GetAllCourse();
    }

    @PostMapping
    public ResponseEntity<CourseDTO> addCourse(@RequestBody Course course) {
        course.setId(0); // chan chan rang du co tinh nhap id da ton tai van khong co loi
        CourseDTO courseDTO = courseService.AddCourse(course) ;
        return ResponseEntity.status(HttpStatus.CREATED).body(courseDTO) ;

    }
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody Course course, @PathVariable int id) {
        CourseDTO updateCourse =  courseService.UpdateCourse(course , id) ;
        return ResponseEntity.ok(updateCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletCourse(@PathVariable int id)
    {
       courseService.RemoveCourse(id);
       return ResponseEntity.ok().build();
    }



}
