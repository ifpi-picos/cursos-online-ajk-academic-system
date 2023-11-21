package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import br.edu.ifpi.entities.Student;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class StudentHomeController implements Initializable {

    private final Connection connection;
    private Student student;

    public StudentHomeController(Connection connection, Student student) {
        this.connection = connection;
        this.student = student;
    }

    @FXML
    private Hyperlink openCourses;

    @FXML
    private Button studentInfo;

    @FXML
    private Text username;

    @FXML
    void usernameInfo(MouseEvent event) {
        System.out.println(this.student.getName());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.username.setText("Bem-vindo, " + this.student.getName());
    }

}
