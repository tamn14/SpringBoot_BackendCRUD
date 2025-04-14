package com.example.SpringBoot_Backend_CRUD.DTO;

public class MarkDTO {
    private long markId;
    private int studentId;
    private int courseId;
    private int lanHoc;
    private Double mark;

    // Constructors
    public MarkDTO() {
    }

    public MarkDTO(long markId, int studentId, int courseId, int lanHoc, Double mark) {
        this.markId = markId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.lanHoc = lanHoc;
        this.mark = mark;
    }

    // Getters and Setters
    public Long getMarkId() {
        return markId;
    }

    public void setMarkId(Long markId) {
        this.markId = markId;
    }

    public void setMarkId(long markId) {
        this.markId = markId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setLanHoc(int lanHoc) {
        this.lanHoc = lanHoc;
    }

    public Integer getLanHoc() {
        return lanHoc;
    }

    public void setLanHoc(Integer lanHoc) {
        this.lanHoc = lanHoc;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }
}
