package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import br.edu.ifpi.entities.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class RegisterCourseController implements Initializable {

    private Connection connection;
    private Student student;

    public RegisterCourseController(Connection connection, Student student) {
        this.connection = connection;
        this.student = student;
    }

    @FXML
    private Button completedCourses;

    @FXML
    private Button exit;

    @FXML
    private Button ongoingCourses;

    @FXML
    private Button register;

    @FXML
    private Button registerCourse;

    @FXML
    private Text username;

    @FXML
    void completedCourses(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {

    }

    @FXML
    void ongoingCourses(ActionEvent event) {

    }

    @FXML
    void register(ActionEvent event) {

    }

    @FXML
    void registerCourse(ActionEvent event) {

    }

    @FXML
    void usernameButton(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
