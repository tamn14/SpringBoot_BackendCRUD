package com.example.SpringBoot_Backend_CRUD.DTO;

import java.util.Date;

public class CourseDTO {
    private int id;
    private String name;
    private Date begin;
    private Date end;
    private int teacherId;
    private String teacherName; 

    // Constructor
    public CourseDTO(int id, String name, Date begin, Date end, int teacherId, String teacherName) {
        this.id = id;
        this.name = name;
        this.begin = begin;
        this.end = end;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
    }

    // Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
