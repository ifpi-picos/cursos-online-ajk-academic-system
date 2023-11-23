package br.edu.ifpi.entities;

import br.edu.ifpi.entities.enums.StatusCourse;

public class Course {

    private int id;
    private String name;
    private StatusCourse status;
    private int workload;
    private int teacherId;

    public Course(int id, String name, StatusCourse status, int workload, int teacherId) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.workload = workload;
        this.teacherId = teacherId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public StatusCourse getStatus() {
        return status;
    }

    public int getWorkload() {
        return workload;
    }

    public int getTeacherId() {
        return teacherId;
    }
}