package br.edu.ifpi.entities;

import br.edu.ifpi.entities.enums.StudentStatus;

public class Student extends User {

    private StudentStatus studentStatus;

    public Student(String name, String email, String password, StudentStatus studentStatus) {
        super(name, email, password);
        this.studentStatus = studentStatus;
    }

    public Student(int id, String name, String email, String password, StudentStatus studentStatus) {
        super(id, name, email, password);
        this.studentStatus = studentStatus;
    }

    public StudentStatus getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(StudentStatus studentStatus) {
        this.studentStatus = studentStatus;
    }
}
