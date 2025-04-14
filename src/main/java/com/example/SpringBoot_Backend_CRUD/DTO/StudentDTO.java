package com.example.SpringBoot_Backend_CRUD.DTO;

import java.util.Date;
import java.util.List;

public class StudentDTO {
    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private boolean isEnrolled;
    private List<Integer> courseIds; // use courseID replance obj Course

    public StudentDTO() {}

    public StudentDTO(int id, String firstName, String lastName, Date birthDate, boolean isEnrolled, List<Integer> courseIds) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.isEnrolled = isEnrolled;
        this.courseIds = courseIds;
    }

    // Getter và Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isEnrolled() {
        return isEnrolled;
    }

    public void setEnrolled(boolean enrolled) {
        isEnrolled = enrolled;
    }

    public List<Integer> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(List<Integer> courseIds) {
        this.courseIds = courseIds;
    }
}