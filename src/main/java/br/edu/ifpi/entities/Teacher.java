package br.edu.ifpi.entities;

import br.edu.ifpi.entities.enums.TeacherStatus;

public class Teacher extends User {

    private TeacherStatus teacherStatus;

    public Teacher(int id, String name, String email, String password, TeacherStatus teacherStatus) {
        super(id, name, email, password);
        this.teacherStatus = teacherStatus;
    }

    public Teacher(String name, String email, String password, TeacherStatus teacherStatus) {
        super(name, email, password);
        this.teacherStatus = teacherStatus;
    }

    public TeacherStatus getTeacherStatus() {
        return teacherStatus;
    }

    public void setTeacherStatus(TeacherStatus teacherStatus) {
        this.teacherStatus = teacherStatus;
    }
}
