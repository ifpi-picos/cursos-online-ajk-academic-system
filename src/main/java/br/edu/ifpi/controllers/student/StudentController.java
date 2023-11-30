package br.edu.ifpi.controllers.student;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import br.edu.ifpi.config.Routes;
import br.edu.ifpi.controllers.LoginController;
import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.StudentCourseDao;
import br.edu.ifpi.entities.Student;
import br.edu.ifpi.util.SceneNavigator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StudentController implements Initializable {

    protected Connection connection;
    protected SceneNavigator sceneNavigator;
    protected Student student;
    protected Stage stage;
    protected LoginController loginController;

    protected CourseDao courseDao;
    protected StudentCourseDao studentCourseDao;

    public StudentController(Connection connection, SceneNavigator sceneNavigator, Student student, Stage stage,
            LoginController loginController, CourseDao courseDao, StudentCourseDao studentCourseDao) {
                
        this.connection = connection;
        this.sceneNavigator = sceneNavigator;
        this.student = student;
        this.stage = stage;
        this.loginController = loginController;
        
        this.courseDao = courseDao;
        this.studentCourseDao = studentCourseDao;
    }

    @FXML
    private Text username;

    @FXML
    void home(ActionEvent event) {
        sceneNavigator.navigateTo(Routes.studentHome, stage, this);
    }

    @FXML
    void completedCourses(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {
        sceneNavigator.navigateTo(Routes.login, this.stage, this.loginController);
    }

    @FXML
    void coursesEnrolled(ActionEvent event) {
        EnrolledCourseController enrolledCourseController = new EnrolledCourseController(
            this.connection, this.sceneNavigator, this.student, this.stage, this.loginController, this.courseDao, this.studentCourseDao
        );
        sceneNavigator.navigateTo(Routes.enrolledCourse, this.stage, enrolledCourseController);
    }

    @FXML
    void register(ActionEvent event) {
        RegisterCourseController registerCourseController = new RegisterCourseController(
            this.connection, this.sceneNavigator, this.student, this.stage, this.loginController, this.courseDao, this.studentCourseDao
        );
        sceneNavigator.navigateTo(Routes.studentRegisterCourse, this.stage, registerCourseController);
    }

    @FXML
    void registerCourse(ActionEvent event) {

    }

    @FXML
    void usernameButton(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("Olá, " + student.getName());
    }
}