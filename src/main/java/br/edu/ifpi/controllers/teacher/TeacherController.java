package br.edu.ifpi.controllers.teacher;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import br.edu.ifpi.configs.Routes;
import br.edu.ifpi.controllers.LoginController;
import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.StudentCourseDao;
import br.edu.ifpi.data.dao.TeacherDao;
import br.edu.ifpi.entities.Teacher;
import br.edu.ifpi.util.Preferences;
import br.edu.ifpi.util.SceneNavigator;
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

public class TeacherController implements Initializable {

    protected Connection connection;
    protected SceneNavigator sceneNavigator;
    protected Teacher teacher;
    protected Stage stage;
    protected LoginController loginController;

    protected CourseDao courseDao;
    protected StudentCourseDao studentCourseDao;
    protected TeacherDao teacherDao;

    protected boolean isDarkMode = false;

    public TeacherController(
            Connection connection,
            SceneNavigator sceneNavigator,
            Teacher teacher,
            Stage stage,
            LoginController loginController,
            CourseDao courseDao,
            TeacherDao teacherDao,
            StudentCourseDao studentCourseDao) {

        this.connection = connection;
        this.sceneNavigator = sceneNavigator;
        this.teacher = teacher;
        this.stage = stage;
        this.loginController = loginController;
        this.courseDao = courseDao;
        this.teacherDao = teacherDao;
        this.studentCourseDao = studentCourseDao;
    }

    @FXML
    private Text username;

    @FXML
    private Button btnMode;

    @FXML
    private ImageView imgMode;

    @FXML
    private BorderPane parent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("Olá, " + teacher.getName());

        isDarkMode = Preferences.isDarkMode();

        if (isDarkMode) {
            setDarkMode();
        } else {
            setLightMode();
        }
    }

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
        TeacherProfileController teacherProfileController = new TeacherProfileController(this);
        sceneNavigator.navigateTo(Routes.teacherProfile, this.stage, teacherProfileController);
    }

    @FXML
    void coursesTaught(ActionEvent event) {
        TeacherCourseController teacherCourseController = new TeacherCourseController(this);
        sceneNavigator.navigateTo(Routes.teacherCourse, this.stage, teacherCourseController);
    }

    @FXML
    void completedCourses(ActionEvent event) {
        TeacherCoursesTaughtController teacherCoursesTaughtController = new TeacherCoursesTaughtController(this);
        sceneNavigator.navigateTo(Routes.teacherCourseTaught, this.stage, teacherCoursesTaughtController);
    }

    @FXML
    void setMode(ActionEvent event) {
        isDarkMode = !isDarkMode;
        if (isDarkMode) {
            setDarkMode();
        } else {
            setLightMode();
        }

        Preferences.setDarkMode(isDarkMode);
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
}