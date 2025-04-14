package com.example.SpringBoot_Backend_CRUD.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mark",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"student_id", "course_id", "lan_hoc"})
        }
)
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long markId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "lan_hoc", nullable = false)
    private Integer lanHoc;

    @Column(nullable = false)
    private Double mark;

    // constructor

    public Mark(Integer lanHoc, Double mark) {
        this.lanHoc = lanHoc;
        this.mark = mark;
    }

    public Mark() {
    }
    // getter and setter

    public Long getMarkId() {
        return markId;
    }

    public void setMarkId(Long markId) {
        this.markId = markId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
