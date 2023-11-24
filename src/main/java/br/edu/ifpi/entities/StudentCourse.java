package br.edu.ifpi.entities;

import br.edu.ifpi.entities.enums.EnrollmentStatus;

public class StudentCourse {

    private Student student;
    private Course course;
    private Double finalGrade;
    private EnrollmentStatus enrollmentStatus;

    public StudentCourse(Student student, Course course, Double finalGrade, EnrollmentStatus enrollmentStatus) {
        this.student = student;
        this.course = course;
        this.finalGrade = finalGrade;
        this.enrollmentStatus = enrollmentStatus;
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
