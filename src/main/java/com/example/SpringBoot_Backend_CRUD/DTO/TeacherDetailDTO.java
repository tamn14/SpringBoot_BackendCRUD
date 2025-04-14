package com.example.SpringBoot_Backend_CRUD.DTO;

public class TeacherDetailDTO {
    private int id;
    private String address;
    private Double salary;
    private String department;

    // Constructors
    public TeacherDetailDTO() {}

    public TeacherDetailDTO(int id, String address, Double salary, String department) {
        this.id = id;
        this.address = address;
        this.salary = salary;
        this.department = department;
    }

    // Getters and setters
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
}
