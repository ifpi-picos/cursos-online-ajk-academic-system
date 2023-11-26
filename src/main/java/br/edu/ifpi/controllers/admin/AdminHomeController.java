package br.edu.ifpi.controllers.admin;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import br.edu.ifpi.config.Routes;
import br.edu.ifpi.controllers.LoginController;
import br.edu.ifpi.entities.Admin;
import br.edu.ifpi.util.SceneNavigator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class AdminHomeController implements Initializable {

    protected Connection connection;
    protected SceneNavigator sceneNavigator;
    protected Admin admin;
    protected Stage stage;

    public AdminHomeController(Connection connection, SceneNavigator sceneNavigator, Admin admin, Stage stage) {
        this.connection = connection;
        this.sceneNavigator = sceneNavigator;
        this.admin = admin;
        this.stage = stage;
    }

    @FXML
    private Text username;

    @FXML
    void closeCourse(ActionEvent event) {

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
    void homeScreen(ActionEvent event) {
        System.out.println("homeScreen");
        AdminHomeController adminHomeController = new AdminHomeController(
                connection,
                sceneNavigator,
                admin,
                stage);
        sceneNavigator.navigateTo(Routes.adminHome, this.stage, adminHomeController);
    }

    @FXML
    void registerCourse(ActionEvent event) {
        AdminRegisterController adminRegisterController = new AdminRegisterController(
                connection,
                sceneNavigator,
                admin,
                stage);
        sceneNavigator.navigateTo(Routes.adminRegisterCourse, this.stage, adminRegisterController);
    }

    @FXML
    void seeCourses(ActionEvent event) {
        AdminSeeCoursesController adminSeeCoursesController = new AdminSeeCoursesController(
                connection,
                sceneNavigator,
                admin,
                stage);
        sceneNavigator.navigateTo(Routes.adminSeeCourses, this.stage, adminSeeCoursesController);
    }

    @FXML
    void seeStudents(ActionEvent event) {
        AdminSeeStudentsController adminSeeStudentsController = new AdminSeeStudentsController(
                connection,
                sceneNavigator,
                admin,
                stage);
        sceneNavigator.navigateTo(Routes.adminSeeStudents, this.stage, adminSeeStudentsController);
    }

    @FXML
    void seeTeatchers(ActionEvent event) {
        AdminSeeTeachersController adminSeeTeacherController = new AdminSeeTeachersController(
                connection,
                sceneNavigator,
                admin,
                stage);
        sceneNavigator.navigateTo(Routes.adminSeeTeachers, this.stage, adminSeeTeacherController);
    }

    @FXML
    void usernameButton(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("Olá, " + admin.getName());
    }

}
