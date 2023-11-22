package br.edu.ifpi.controllers.student;

import java.sql.Connection;

import br.edu.ifpi.entities.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RegisterCourseController extends StudentHomeController {

    public RegisterCourseController(Connection connection, Student student) {
        super(connection, student);
    }

    @FXML
    private Button registerCourse;


    @FXML
    void registerCourse(ActionEvent event) {

    }
}
