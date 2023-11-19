package br.edu.ifpi.entities;

public class Student {
    private int id;
    private String name;
    private String email;
    private Course courses;

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public Course getCourse() {
        return courses;
    }

    public void setCourses(Course courses) {
        this.courses = courses;
    }
}
