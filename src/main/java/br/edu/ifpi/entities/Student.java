package br.edu.ifpi.entities;

import br.edu.ifpi.entities.enums.StudentStatus;

public class Student {
    private int id;
    private String name;
    private String email;
    private String password;
    private StudentStatus studentStatus;

    public Student(String name, String email, String password, StudentStatus studentStatus) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.studentStatus = studentStatus;
    }

    public Student(int id, String name, String email, String password, StudentStatus studentStatus) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.studentStatus = studentStatus;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmai(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public StudentStatus getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(StudentStatus studentStatus) {
        this.studentStatus = studentStatus;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
