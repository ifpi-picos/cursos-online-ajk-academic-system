package br.edu.ifpi.entities;

import br.edu.ifpi.entities.enums.StatusCourse;

public class StudentCourse {

    private Integer studentId;
    private Integer courseId;
    private Double finalGrade;
    private StatusCourse status;

    public StudentCourse(Integer studentId, Integer courseId, Double finalGrade, StatusCourse status) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.finalGrade = finalGrade;
        this.status = status;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public Integer getCourseId() {
        return courseId;
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
