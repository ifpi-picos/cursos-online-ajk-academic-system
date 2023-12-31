package br.edu.ifpi.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Hyperlink;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import br.edu.ifpi.configs.Routes;
import br.edu.ifpi.controllers.admin.AdminController;
import br.edu.ifpi.controllers.student.StudentController;
import br.edu.ifpi.controllers.teacher.TeacherController;
import br.edu.ifpi.data.dao.AdminDao;
import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.StudentCourseDao;
import br.edu.ifpi.data.dao.StudentDao;
import br.edu.ifpi.data.dao.TeacherDao;
import br.edu.ifpi.entities.Admin;
import br.edu.ifpi.entities.Student;
import br.edu.ifpi.entities.Teacher;
import br.edu.ifpi.util.AlertMessage;
import br.edu.ifpi.util.SceneNavigator;

public class LoginController implements Initializable {

    private final Connection connection;

    private final AdminDao adminDao;
    private final TeacherDao teacherDao;
    private final StudentDao studentDao;
    private final CourseDao courseDao;
    private final StudentCourseDao studentCourseDao;

    private final SceneNavigator sceneNavigator;
    private Stage stage;

    public LoginController(
            Connection connection,
            AdminDao adminDao,
            TeacherDao teacherDao,
            StudentDao studentDao,
            CourseDao courseDao,
            StudentCourseDao studentCourseDao,
            SceneNavigator sceneNavigator,
            Stage stage) {

        this.connection = connection;
        this.adminDao = adminDao;
        this.teacherDao = teacherDao;
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.studentCourseDao = studentCourseDao;
        this.sceneNavigator = sceneNavigator;
        this.stage = stage;
    }

    @FXML
    private Hyperlink createAccount;

    @FXML
    private ToggleGroup grupo;

    @FXML
    private PasswordField password;

    @FXML
    private RadioButton student;

    @FXML
    private RadioButton teacher;

    @FXML
    private RadioButton admin;

    @FXML
    private TextField email;

    @FXML
    void login(ActionEvent event) {
        String email = this.email.getText();
        String password = this.password.getText();

        Boolean isStudent = this.student.isSelected();
        Boolean isTeacher = this.teacher.isSelected();
        Boolean isAdmin = this.admin.isSelected();

        if (email.isEmpty() || password.isEmpty()) {
            AlertMessage.show("Erro ao fazer login", "", "Preencha todos os campos", AlertType.ERROR);
            return;
        }

        if (isStudent) {
            loginStudent(email, password);
        } else if (isTeacher) {
            loginTeacher(email, password);
        } else if (isAdmin) {
            loginAdmin(email, password);
        }
    }

    private void loginAdmin(String email, String password) {
        Admin admin = adminDao.login(email, password);

        if (admin != null) {
            try {
                AdminController adminHomeController = new AdminController(
                        connection, sceneNavigator, admin, stage, courseDao, teacherDao, studentDao, adminDao, this);
                sceneNavigator.navigateTo(Routes.adminHome, this.stage, adminHomeController);
            } catch (Exception e) {
                AlertMessage.show("Erro ao fazer login", "", "Ocorreu um erro ao fazer login",
                        AlertType.ERROR);
                System.err.println(e.getMessage());
            }
        } else {
            AlertMessage.show("Erro ao fazer login", "", "Usuário ou senha incorretos", AlertType.ERROR);
        }
    }

    private void loginTeacher(String email, String password) {
        Teacher teacher = teacherDao.login(email, password);

        if (teacher != null) {
            try {
                TeacherController teacherHomeController = new TeacherController(
                        connection, sceneNavigator, teacher, stage, this, courseDao, teacherDao, studentCourseDao);
                sceneNavigator.navigateTo(Routes.teacherHome, this.stage, teacherHomeController);
            } catch (Exception e) {
                AlertMessage.show("Erro ao fazer login", "", "Ocorreu um erro ao fazer login",
                        AlertType.ERROR);
            }
        } else {
            AlertMessage.show("Erro ao fazer login", "", "Usuário ou senha incorretos", AlertType.ERROR);
        }
    }

    private void loginStudent(String email, String password) {
        Student student = studentDao.login(email, password);

        if (student != null) {
            try {
                StudentController studentHomeController = new StudentController(connection, sceneNavigator, student,
                        stage, this, courseDao, studentCourseDao, studentDao);
                sceneNavigator.navigateTo(Routes.studentHome, this.stage, studentHomeController);
            } catch (Exception e) {
                AlertMessage.show("Erro ao fazer login", "", "Ocorreu um erro ao fazer login",
                        AlertType.ERROR);
            }
        } else {
            AlertMessage.show("Erro ao fazer login", "", "Usuário ou senha incorretos", AlertType.ERROR);
        }
    }

    @FXML
    void newAccount(ActionEvent event) {
        try {
            RegisterController registerController = new RegisterController(
                    connection, stage, sceneNavigator, this);
            sceneNavigator.navigateTo(Routes.register, this.stage, registerController);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
