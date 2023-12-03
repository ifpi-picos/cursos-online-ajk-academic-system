package br.edu.ifpi.controllers.teacher;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import br.edu.ifpi.config.Routes;
import br.edu.ifpi.controllers.LoginController;
import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.StudentCourseDao;
import br.edu.ifpi.entities.User;
import br.edu.ifpi.util.SceneNavigator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TeacherController implements Initializable {

    protected Connection connection;
    protected SceneNavigator sceneNavigator;
    protected User teacher;
    protected Stage stage;
    protected LoginController loginController;
    protected CourseDao courseDao;
    protected StudentCourseDao studentCourseDao;

    public TeacherController(
            Connection connection,
            SceneNavigator sceneNavigator,
            User teacher,
            Stage stage,
            LoginController loginController,
            CourseDao courseDao,
            StudentCourseDao studentCourseDao) {

        this.connection = connection;
        this.sceneNavigator = sceneNavigator;
        this.teacher = teacher;
        this.stage = stage;
        this.loginController = loginController;
        this.courseDao = courseDao;
        this.studentCourseDao = studentCourseDao;
    }

    @FXML
    private Text username;

    @FXML
    void home(ActionEvent event) {
        sceneNavigator.navigateTo(Routes.teacherHome, this.stage, this);
    }

    @FXML
    void exit(ActionEvent event) {
        sceneNavigator.navigateTo(Routes.login, this.stage, loginController);
    }

    @FXML
    void usernameButton(MouseEvent event) {
        TeacherProfileController teacherProfileController = new TeacherProfileController(
                this.connection, this.sceneNavigator, this.teacher, this.stage, this.loginController, this.courseDao,
                this.studentCourseDao);
        sceneNavigator.navigateTo(Routes.teacherProfile, this.stage, teacherProfileController);
    }

    @FXML
    void coursesTaught(ActionEvent event) {
        TeacherCourseController teacherCourseController = new TeacherCourseController(
                this.connection, this.sceneNavigator, this.teacher, this.stage, this.loginController, this.courseDao,
                this.studentCourseDao);
        sceneNavigator.navigateTo(Routes.teacherCourse, this.stage, teacherCourseController);
    }

    @FXML
    void completedCourses(ActionEvent event) {
        TeacherCoursesTaughtController teacherCoursesTaughtController = new TeacherCoursesTaughtController(
                this.connection, this.sceneNavigator, this.teacher, this.stage, this.loginController, this.courseDao,
                this.studentCourseDao);
        sceneNavigator.navigateTo(Routes.teacherCourseTaught, this.stage, teacherCoursesTaughtController);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("Olá, " + teacher.getName());
    }

}