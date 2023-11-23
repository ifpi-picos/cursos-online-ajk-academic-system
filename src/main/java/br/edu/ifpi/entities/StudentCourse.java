package br.edu.ifpi.entities;

import br.edu.ifpi.entities.enums.StatusCourse;

public class StudentCourse {

    private Student student;
    private Course course;
    private Double finalGrade;
    private StatusCourse status;

    public StudentCourse(Student student, Course course, Double finalGrade, StatusCourse status) {
        this.student = student;
        this.course = course;
        this.finalGrade = finalGrade;
        this.status = status;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public Double getFinalGrade() {
        return finalGrade;
    }

    public StatusCourse getStatus() {
        return status;
    }
    
    public void setFinalGrade(Double finalGrade) {
        this.finalGrade = finalGrade;
    }

    public void setStatus(StatusCourse status) {
        this.status = status;
    }
}
