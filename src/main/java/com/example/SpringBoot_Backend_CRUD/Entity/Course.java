package com.example.SpringBoot_Backend_CRUD.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {
//    config properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id ;
    @Column(name = "name" , length = 50)
    private String name;
    @Column(name = "begin")
    private Date begin ;
    @Column(name = "end")
    private  Date end ;
    @ManyToMany(mappedBy = "courses", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE ,
            CascadeType.PERSIST ,
            CascadeType.REFRESH
    })
    private List<Student> students = new ArrayList<>() ;

    @ManyToOne(fetch = FetchType.LAZY ,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE ,
                    CascadeType.PERSIST ,
                    CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "teacher_ID")
    private Teacher teacher ;
    @OneToMany(mappedBy = "course",  cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE ,
            CascadeType.PERSIST ,
            CascadeType.REFRESH ,
            CascadeType.REMOVE
    })
    private List<Mark> marks = new ArrayList<>();

    // Constructor
    public Course(String name, Date begin, Date end) {
        this.name = name;
        this.begin = begin;
        this.end = end;
    }

    public Course() {
    }
    // Getter and setter
    public int getId() {
        return id;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
// Synchronizes data

    public void addMark(Mark mark){
        this.marks.add(mark);
        mark.setCourse(this);
    }
    public void removeMark(Mark mark){
        this.marks.remove(mark);
        mark.setCourse(null);
    }


}
