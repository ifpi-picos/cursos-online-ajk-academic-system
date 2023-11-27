package br.edu.ifpi.controllers.teacher;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import br.edu.ifpi.config.Routes;
import br.edu.ifpi.controllers.LoginController;
import br.edu.ifpi.entities.Teacher;
import br.edu.ifpi.util.SceneNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TeacherHomeController implements Initializable {

    protected Connection connection;
    protected SceneNavigator sceneNavigator;
    protected Teacher teacher;
    protected Stage stage;

    public TeacherHomeController(Connection connection, SceneNavigator sceneNavigator, Teacher teacher, Stage stage) {
        this.connection = connection;
        this.sceneNavigator = sceneNavigator;
        this.teacher = teacher;
        this.stage = stage;
    }

    @FXML
    private Text username;

    @FXML
    void home(ActionEvent event) {
        TeacherHomeController teacherHomeController = new TeacherHomeController(
                connection,
                sceneNavigator,
                teacher,
                stage);
        sceneNavigator.navigateTo(Routes.teacherHome, this.stage, teacherHomeController);
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
    void usernameButton(MouseEvent event) {

    }


    @FXML
    void coursesTaught(ActionEvent event) {
        TeacherCourseController teacherCourseController = new TeacherCourseController(
                connection,
                sceneNavigator,
                teacher,
                stage);

        sceneNavigator.navigateTo(Routes.teacherCourse, this.stage, teacherCourseController);
    }

    @FXML
    void completedCourses(ActionEvent event) {
        TeacherCourseCompleted teacherCourseCompleted = new TeacherCourseCompleted(
                connection,
                sceneNavigator,
                teacher,
                stage);

        sceneNavigator.navigateTo(Routes.teacherCourseCompleted, this.stage, teacherCourseCompleted);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("Olá, " + teacher.getName());
    }

}