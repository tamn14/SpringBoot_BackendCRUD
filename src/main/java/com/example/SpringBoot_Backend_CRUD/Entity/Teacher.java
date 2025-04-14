package com.example.SpringBoot_Backend_CRUD.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id ;
    @Column(name = "lastname" , length = 25)
    private String lastName ;
    @Column(name = "firstname" , length = 25)
    private String firstName ;
    @Column(name = "email")
    private String email ;
    @OneToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "detail_id")
    private TeacherDetail teacherDetail ;
    @OneToMany(fetch = FetchType.LAZY ,
                cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE ,
                    CascadeType.PERSIST ,
                    CascadeType.REFRESH
                },
            mappedBy = "teacher"
    )
    private List<Course> courses = new ArrayList<>();

    // constructor
    public Teacher(String lastName, String firstName, String email, TeacherDetail teacherDetail) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.teacherDetail = teacherDetail;
    }

    public Teacher() {
    }
    // getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TeacherDetail getTeacherDetail() {
        return teacherDetail;
    }

    public void setTeacherDetail(TeacherDetail teacherDetail) {
        this.teacherDetail = teacherDetail;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    // Synchronizes data
    public void AddCourse(Course course) {
        this.courses.add(course);
        course.setTeacher(this);
    }
    public void DeleteCourse(Course course) {
        this.courses.remove(course);
        course.setTeacher(null);
    }

    public void AddDetail(TeacherDetail teacherDetail) {
        this.setTeacherDetail(teacherDetail);
        teacherDetail.setTeacher(this);
    }




}
