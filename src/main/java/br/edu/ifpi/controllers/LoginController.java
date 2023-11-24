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

import br.edu.ifpi.config.Routes;
import br.edu.ifpi.controllers.student.StudentHomeController;
import br.edu.ifpi.data.dao.StudentDao;
import br.edu.ifpi.entities.Student;
import br.edu.ifpi.util.AlertMessage;
import br.edu.ifpi.util.SceneNavigator;

public class LoginController implements Initializable {

    private final Connection connection;
    private SceneNavigator sceneNavigator;
    private Stage stage;

    public LoginController(Connection connection, Stage stage, SceneNavigator sceneNavigator) {
        this.connection = connection;
        this.stage = stage;
        this.sceneNavigator = sceneNavigator;
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
    private TextField username;

    @FXML
    void login(ActionEvent event) {
        String username = this.username.getText();
        String password = this.password.getText();
        Boolean isStudent = this.student.isSelected();
        Boolean isTeacher = this.teacher.isSelected();
        Boolean isAdmin = this.admin.isSelected();

        if (username.isEmpty() || password.isEmpty()) {
            AlertMessage.show("Erro", "Erro ao fazer login", "Preencha todos os campos", AlertType.ERROR);
        } else {
            if (isStudent) {
                StudentDao studentDao = new StudentDao(this.connection);
                Student student = studentDao.login(username, password);

                if (student != null) {
                    try {
                        StudentHomeController studentHomeController = new StudentHomeController(
                                connection,
                                sceneNavigator,
                                student,
                                stage);
                        sceneNavigator.navigateTo(Routes.studentHome, this.stage, studentHomeController);
                    } catch (Exception e) {
                        AlertMessage.show("Erro", "Erro ao fazer login", "Ocorreu um erro ao fazer login",
                                AlertType.ERROR);
                    }
                } else {
                    AlertMessage.show("Erro", "Erro ao fazer login", "Usuário ou senha incorretos", AlertType.ERROR);
                }
            } else if (isTeacher) {
                // TODO: implementar a lógica de login para professores
            } else if (isAdmin) {
                // TODO: implementar a lógica de login para administradores
            }
        }
    }

    @FXML
    void newAccount(ActionEvent event) {
        try {
            RegisterController registerController = new RegisterController(
                    this.connection,
                    this.stage,
                    this.sceneNavigator);

            sceneNavigator.navigateTo(Routes.register, this.stage, registerController);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
