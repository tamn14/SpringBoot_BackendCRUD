package com.example.SpringBoot_Backend_CRUD.DTO;

import java.util.List;

public class TeacherDTO {
    private int id;
    private String lastName;
    private String firstName;
    private String email;
    private TeacherDetailDTO teacherDetail;


    // Constructors
    public TeacherDTO() {}

    public TeacherDTO(int id, String lastName, String firstName, String email, TeacherDetailDTO teacherDetail) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.teacherDetail = teacherDetail;

    }

    // Getters and setters
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

    public TeacherDetailDTO getTeacherDetail() {
        return teacherDetail;
    }

    public void setTeacherDetail(TeacherDetailDTO teacherDetail) {
        this.teacherDetail = teacherDetail;
    }


}
