package br.edu.ifpi.controllers.student;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import br.edu.ifpi.config.Routes;
import br.edu.ifpi.controllers.LoginController;
import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.StudentCourseDao;
import br.edu.ifpi.data.dao.StudentDao;
import br.edu.ifpi.entities.Student;
import br.edu.ifpi.util.SceneNavigator;
import br.edu.ifpi.util.prefs.PreferencesUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
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
    protected StudentDao studentDao;

    protected boolean isDarkMode = false;

    public StudentController(
            Connection connection,
            SceneNavigator sceneNavigator,
            Student student,
            Stage stage,
            LoginController loginController,
            CourseDao courseDao,
            StudentCourseDao studentCourseDao,
            StudentDao studentDao) {

        this.connection = connection;
        this.sceneNavigator = sceneNavigator;
        this.student = student;
        this.stage = stage;
        this.loginController = loginController;

        this.courseDao = courseDao;
        this.studentCourseDao = studentCourseDao;
        this.studentDao = studentDao;
    }

    @FXML
    private Button btnMode;

    @FXML
    private Button exit;

    @FXML
    private ImageView imgMode;

    @FXML
    private BorderPane parent;

    @FXML
    private Text username;

    @FXML
    void home(ActionEvent event) {
        sceneNavigator.navigateTo(Routes.studentHome, stage, this);
    }

    @FXML
    void completedCourses(ActionEvent event) {
        CompletedCourseController completedCourseController = new CompletedCourseController(
                this.connection, this.sceneNavigator, this.student, this.stage, this.loginController, this.courseDao,
                this.studentCourseDao, this.studentDao);
        sceneNavigator.navigateTo(Routes.completedCourse, this.stage, completedCourseController);
    }

    @FXML
    void exit(ActionEvent event) {
        sceneNavigator.navigateTo(Routes.login, this.stage, this.loginController);
    }

    @FXML
    void coursesEnrolled(ActionEvent event) {
        EnrolledCourseController enrolledCourseController = new EnrolledCourseController(
                this.connection, this.sceneNavigator, this.student, this.stage, this.loginController, this.courseDao,
                this.studentCourseDao, this.studentDao);
        sceneNavigator.navigateTo(Routes.enrolledCourse, this.stage, enrolledCourseController);
    }

    @FXML
    void register(ActionEvent event) {
        RegisterCourseController registerCourseController = new RegisterCourseController(
                this.connection, this.sceneNavigator, this.student, this.stage, this.loginController, this.courseDao,
                this.studentCourseDao, this.studentDao);
        sceneNavigator.navigateTo(Routes.studentRegisterCourse, this.stage, registerCourseController);
    }

    @FXML
    void registerCourse(ActionEvent event) {

    }

    @FXML
    void usernameButton(MouseEvent event) {
        StudentProfileController studentProfileController = new StudentProfileController(
                this.connection, this.sceneNavigator, this.student, this.stage, this.loginController, this.courseDao,
                this.studentDao, this.studentCourseDao);
        sceneNavigator.navigateTo(Routes.studentProfile, this.stage, studentProfileController);

    }

    @FXML
    void setMode(ActionEvent event) {
        isDarkMode = !isDarkMode;
        if (isDarkMode) {
            setDarkMode();
        } else {
            setLightMode();
        }

        PreferencesUtil.setDarkMode(isDarkMode);
    }

    protected void setDarkMode() {
        parent.getStylesheets().remove(getClass().getResource(Routes.lightMode).toExternalForm());
        parent.getStylesheets().add(getClass().getResource(Routes.darkMode).toExternalForm());
        Image image = new Image(getClass().getResourceAsStream(Routes.imgSun));
        imgMode.setImage(image);
    }

    protected void setLightMode() {
        parent.getStylesheets().remove(getClass().getResource(Routes.darkMode).toExternalForm());
        parent.getStylesheets().add(getClass().getResource(Routes.lightMode).toExternalForm());
        Image image = new Image(getClass().getResourceAsStream(Routes.imgMoon));
        imgMode.setImage(image);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("Olá, " + student.getName());

        isDarkMode = PreferencesUtil.isDarkMode();

        if (isDarkMode) {
            setDarkMode();
        } else {
            setLightMode();
        }
    }
}