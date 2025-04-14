package com.example.SpringBoot_Backend_CRUD.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "teacherdetail")
public class TeacherDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "address")
    private String address ;
    @Column(name = "salary")
    private Double salary ;
    @Column(name = "department")
    private String department ;
    @OneToOne(mappedBy = "teacherDetail")
    private Teacher teacher ;

    // constructor


    public TeacherDetail(String address, Double salary, String department) {
        this.address = address;
        this.salary = salary;
        this.department = department;
    }

    public TeacherDetail() {
    }
    //getter and setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    // Synchronizes data
    public void AddTeacher(Teacher teacher) {
        this.setTeacher(teacher);
        teacher.setTeacherDetail(this);
    }
}
