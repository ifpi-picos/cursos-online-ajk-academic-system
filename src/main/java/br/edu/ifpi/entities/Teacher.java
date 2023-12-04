package br.edu.ifpi.entities;

import br.edu.ifpi.entities.enums.TeacherStatus;

public class Teacher {
    
    private String name;
    private int id;
    private String email;
    private String password;
    private TeacherStatus teacherStatus;

    public Teacher(int id, String name, String email, String password, TeacherStatus teacherStatus) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.teacherStatus = teacherStatus;
    }

    public Teacher(String name, String email, String password, TeacherStatus teacherStatus) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.teacherStatus = teacherStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public TeacherStatus getTeacherStatus() {
        return teacherStatus;
    }

    public void setTeacherStatus(TeacherStatus teacherStatus) {
        this.teacherStatus = teacherStatus;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
