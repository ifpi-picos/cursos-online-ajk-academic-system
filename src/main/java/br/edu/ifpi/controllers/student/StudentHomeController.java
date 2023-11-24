package br.edu.ifpi.controllers.student;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import br.edu.ifpi.config.Routes;
import br.edu.ifpi.controllers.LoginController;
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
    protected SceneNavigator sceneNavigator;
    protected Student student;
    protected Stage stage;

    public StudentHomeController(Connection connection, SceneNavigator sceneNavigator, Student student, Stage stage) {
        this.connection = connection;
        this.sceneNavigator = sceneNavigator;
        this.student = student;
        this.stage = stage;
    }

    @FXML
    private Text username;

    @FXML
    void home(ActionEvent event) {
        StudentHomeController studentHomeController = new StudentHomeController(
                connection,
                sceneNavigator,
                student,
                stage);
        sceneNavigator.navigateTo(Routes.studentHome, this.stage, studentHomeController);
    }

    @FXML
    void completedCourses(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {
        LoginController loginController = new LoginController(
                connection,
                stage,
                sceneNavigator);

        sceneNavigator.navigateTo(Routes.login, this.stage, loginController);
    }

    @FXML
    void coursesEnrolled(ActionEvent event) {
        System.out.println("coursesEnrolled");

    }

    @FXML
    void register(ActionEvent event) {
        RegisterCourseController registerCourseController = new RegisterCourseController(
                connection,
                sceneNavigator,
                stage,
                student);
        sceneNavigator.navigateTo(Routes.registerCourse, this.stage, registerCourseController);
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