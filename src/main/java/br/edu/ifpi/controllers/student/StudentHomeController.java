package br.edu.ifpi.controllers.student;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import br.edu.ifpi.config.Routes;
import br.edu.ifpi.controllers.RegisterController;
import br.edu.ifpi.entities.Student;
import br.edu.ifpi.util.SceneNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StudentHomeController implements Initializable {

    protected Connection connection;
    private Student student;

    public StudentHomeController(Connection connection, Student student) {
        this.connection = connection;
        this.student = student;
    }

    @FXML
    private Text username;

    @FXML
    void home(ActionEvent event) {
        SceneNavigator sceneNavigator = new SceneNavigator();
        Stage stage = (Stage) username.getScene().getWindow();
        StudentHomeController studentHomeController = new StudentHomeController(connection, student);
        sceneNavigator.navigateTo(Routes.studentHome, stage, studentHomeController, true);
    }

    @FXML
    void completedCourses(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {
        System.out.println("Saindo...");
    }

    @FXML
    void ongoingCourses(ActionEvent event) {

    }

    @FXML
    void register(ActionEvent event) {
        SceneNavigator sceneNavigator = new SceneNavigator();
        Stage stage = (Stage) username.getScene().getWindow();
        RegisterCourseController registerController = new RegisterCourseController(connection, student);
        sceneNavigator.navigateTo(Routes.registerCourse, stage, registerController, true);
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