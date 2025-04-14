package com.example.SpringBoot_Backend_CRUD.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id ;
    @Column(name = "lastname" , length = 25, nullable = false)
    private String lastName ;
    @Column(name = "firstname" , length = 25 , nullable = false)
    private String firstName ;
    @Column(name = "birthday", nullable = false)
    private Date birthDay ;
    @Column(name = "is_enrolled")
    private boolean isEnrolled ;

    @ManyToMany(fetch = FetchType.LAZY ,
                cascade = {
                        CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
                }
    )
    @JoinTable(
            name = "Course_Student",
            joinColumns = @JoinColumn(name = "Student_ID"),
            inverseJoinColumns = @JoinColumn(name = "Course_ID")
    )
    private List<Course> courses = new ArrayList<>() ;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Mark> marks = new ArrayList<>();

    // constructor
    public Student(String lastName, String firstName, Date birthDay, boolean isEnrolled) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDay = birthDay;
        this.isEnrolled = isEnrolled;
    }

    public Student() {
    }
    // Getter and Setter
    public int getId() {
        return id;
    }


    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
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

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public boolean isEnrolled() {
        return isEnrolled;
    }

    public void setEnrolled(boolean enrolled) {
        isEnrolled = enrolled;
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
        course.getStudents().add(this);
    }
    public void DeleteCourse(Course course) {
        this.courses.remove(course);
        course.getStudents().remove(this);
    }

    public void addMark(Mark mark){
        this.marks.add(mark);
        mark.setStudent(this);
    }

    public void removeMark(Mark mark){
        this.marks.remove(mark);
        mark.setStudent(null);
    }

}
