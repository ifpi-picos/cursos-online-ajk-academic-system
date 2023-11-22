package br.edu.ifpi.controllers.student;

import java.sql.Connection;

import br.edu.ifpi.entities.Student;
import br.edu.ifpi.util.SceneNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RegisterCourseController extends StudentHomeController {

    public RegisterCourseController(Connection connection, SceneNavigator sceneNavigator, Stage stage, Student student) {
        super(connection, sceneNavigator, stage, student);
    }

    @FXML
    private Button registerCourse;


    @FXML
    void registerCourse(ActionEvent event) {

    }
}
