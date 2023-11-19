package br.edu.ifpi.entities;

public class Grade {

    private int id;
    private int studentId;
    private int courseId;
    private float grade;

    public Grade(int id, int studentId, int courseId, float grade) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

    public Grade(int studentId, int courseId, float grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public float getGrade() {
        return grade;
    }
}
