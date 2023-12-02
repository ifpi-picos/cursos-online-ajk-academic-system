package br.edu.ifpi.entities;

import br.edu.ifpi.entities.enums.EnrollmentStatus;

public class StudentCourse {

    private User student;
    private Course course;
    private Double finalGrade;
    private EnrollmentStatus enrollmentStatus;

    public StudentCourse(User student, Course course, Double finalGrade, EnrollmentStatus enrollmentStatus) {
        this.student = student;
        this.course = course;
        this.finalGrade = finalGrade;
        this.enrollmentStatus = enrollmentStatus;
    }

    public User getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public Double getFinalGrade() {
        return finalGrade;
    }

    public EnrollmentStatus getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setFinalGrade(Double finalGrade) {
        this.finalGrade = finalGrade;
    }

    public void setEnrollmentStatus(EnrollmentStatus enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }
}
