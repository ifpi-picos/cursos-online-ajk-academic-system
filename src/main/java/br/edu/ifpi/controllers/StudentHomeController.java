package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import br.edu.ifpi.entities.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class StudentHomeController implements Initializable {

    private final Connection connection;
    private Student student;

    public StudentHomeController(Connection connection, Student student) {
        this.connection = connection;
        this.student = student;
    }

    @FXML
    private TextFlow usernameButton;

    @FXML
    private Text usernameText;

    @FXML
    void completedCourses(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {
        System.out.println("Botão sair");

    }

    @FXML
    void ongoingCourses(ActionEvent event) {

    }

    @FXML
    void register(ActionEvent event) {

    }

    @FXML
    void usernameButtonClick(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.usernameText.setText(this.student.getName());
    }

}