package br.edu.ifpi.controllers.admin;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import br.edu.ifpi.config.Routes;
import br.edu.ifpi.controllers.LoginController;
import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.StudentDao;
import br.edu.ifpi.data.dao.TeacherDao;
import br.edu.ifpi.entities.User;
import br.edu.ifpi.util.SceneNavigator;
import br.edu.ifpi.util.prefs.PreferencesUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class AdminController implements Initializable {

    protected Connection connection;
    protected SceneNavigator sceneNavigator;
    protected User admin;
    protected Stage stage;
    protected LoginController loginController;

    protected CourseDao courseDao;
    protected TeacherDao teacherDao;
    protected StudentDao studentDao;

    public AdminController(
            Connection connection,
            SceneNavigator sceneNavigator,
            User admin,
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
        AdminProfileController adminProfileController = new AdminProfileController(
            this.connection, this.sceneNavigator, this.admin, this.stage, this.courseDao, this.studentDao, this.loginController, this.teacherDao); 
        sceneNavigator.navigateTo(Routes.adminProfile, this.stage, adminProfileController);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("Olá, " + admin.getName());

        isDarkMode = PreferencesUtil.isDarkMode();

        if (isDarkMode) {
            setDarkMode();
        } else {
            setLightMode();
        }
    }

    @FXML
    private Button btnMode;

    @FXML
    private ImageView imgMode;

    @FXML
    private BorderPane parent;

    private boolean isDarkMode = false;

    public void setMode(ActionEvent event) {
        isDarkMode = !isDarkMode;
        if(isDarkMode) {
            setDarkMode();
        } else {
            setLightMode();
        }

        PreferencesUtil.setDarkMode(isDarkMode);
    }

    private void setDarkMode() {
        parent.getStylesheets().remove(getClass().getResource(Routes.lightMode).toExternalForm());
        parent.getStylesheets().add(getClass().getResource(Routes.darkMode).toExternalForm());
        Image image = new Image(getClass().getResourceAsStream(Routes.imgSun));
        imgMode.setImage(image);
    }

    private void setLightMode() {
        parent.getStylesheets().remove(getClass().getResource(Routes.darkMode).toExternalForm());
        parent.getStylesheets().add(getClass().getResource(Routes.lightMode).toExternalForm());
        Image image = new Image(getClass().getResourceAsStream(Routes.imgMoon));
        imgMode.setImage(image);
    }
}
