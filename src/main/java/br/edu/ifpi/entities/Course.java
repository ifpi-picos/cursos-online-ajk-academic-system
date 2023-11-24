package br.edu.ifpi.entities;

import br.edu.ifpi.entities.enums.CourseStatus;

public class Course {

    private int id;
    private String name;
    private CourseStatus status;
    private int workload;
    private Teacher teacher;

    public Course(String name, CourseStatus status, int workload, Teacher teacher) {
        this.name = name;
        this.status = status;
        this.workload = workload;
        this.teacher = teacher;
    }

    public Course(int id, String name, CourseStatus status, int workload, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.workload = workload;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public int getWorkload() {
        return workload;
    }

    public Teacher getTeacher() {
        return teacher;
    }
}