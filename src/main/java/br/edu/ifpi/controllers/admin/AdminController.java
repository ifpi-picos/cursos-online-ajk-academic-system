package br.edu.ifpi.controllers.admin;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import br.edu.ifpi.config.Routes;
import br.edu.ifpi.controllers.LoginController;
import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.StudentDao;
import br.edu.ifpi.data.dao.TeacherDao;
import br.edu.ifpi.entities.Admin;
import br.edu.ifpi.util.SceneNavigator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class AdminController implements Initializable {

    protected Connection connection;
    protected SceneNavigator sceneNavigator;
    protected Admin admin;
    protected Stage stage;
    protected LoginController loginController;

    protected CourseDao courseDao;
    protected TeacherDao teacherDao;
    protected StudentDao studentDao;

    public AdminController(
            Connection connection,
            SceneNavigator sceneNavigator,
            Admin admin,
            Stage stage,
            CourseDao courseDao,
            TeacherDao teacherDao,
            StudentDao studentDao,
            LoginController loginController) {
                
        this.connection = connection;
        this.sceneNavigator = sceneNavigator;
        this.admin = admin;
        this.stage = stage;
        this.loginController = loginController;

        this.courseDao = courseDao;
        this.teacherDao = teacherDao;
        this.studentDao = studentDao;
    }

    @FXML
    private Text username;

    @FXML
    void closeCourse(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {
        sceneNavigator.navigateTo(Routes.login, this.stage, loginController);
    }

    @FXML
    void homeScreen(ActionEvent event) {
        sceneNavigator.navigateTo(Routes.adminHome, this.stage, this);
    }

    @FXML
    void registerCourse(ActionEvent event) {
        AdminRegisterController adminRegisterController = new AdminRegisterController(connection, sceneNavigator, admin,
                stage, courseDao, teacherDao, studentDao, loginController);
        sceneNavigator.navigateTo(Routes.adminRegisterCourse, this.stage, adminRegisterController);
    }

    @FXML
    void seeCourses(ActionEvent event) {
        AdminSeeCoursesController adminSeeCoursesController = new AdminSeeCoursesController(connection, sceneNavigator,
                admin, stage, courseDao, teacherDao, studentDao, loginController);
        sceneNavigator.navigateTo(Routes.adminSeeCourses, this.stage, adminSeeCoursesController);
    }

    @FXML
    void seeStudents(ActionEvent event) {
        AdminSeeStudentsController adminSeeStudentsController = new AdminSeeStudentsController(connection,
                sceneNavigator, admin, stage, courseDao, teacherDao, studentDao, loginController);
        sceneNavigator.navigateTo(Routes.adminSeeStudents, this.stage, adminSeeStudentsController);
    }

    @FXML
    void seeTeatchers(ActionEvent event) {
        AdminSeeTeachersController adminSeeTeachersController = new AdminSeeTeachersController(connection,
                sceneNavigator, admin, stage, courseDao, teacherDao, studentDao, loginController);
        sceneNavigator.navigateTo(Routes.adminSeeTeachers, this.stage, adminSeeTeachersController);
    }

    @FXML
    void usernameButton(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("Olá, " + admin.getName());
    }

}
