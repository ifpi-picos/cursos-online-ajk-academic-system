package br.edu.ifpi.controllers.student;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import br.edu.ifpi.config.Routes;
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
    private SceneNavigator sceneNavigator;
    private Stage stage;
    private Student student;

    public StudentHomeController(Connection connection, SceneNavigator sceneNavigator, Stage stage, Student student) {
        this.connection = connection;
        this.sceneNavigator = sceneNavigator;
        this.stage = stage;
        this.student = student;
    }

    @FXML
    private Text username;

    @FXML
    void home(ActionEvent event) {
        StudentHomeController studentHomeController = new StudentHomeController(
                connection,
                sceneNavigator,
                stage,
                student);
        sceneNavigator.navigateTo(Routes.studentHome, this.stage, studentHomeController, true);
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
        RegisterCourseController registerCourseController = new RegisterCourseController(
                connection,
                sceneNavigator,
                stage,
                student);
        sceneNavigator.navigateTo(Routes.registerCourse, this.stage, registerCourseController, true);
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