package br.edu.ifpi.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
import br.edu.ifpi.data.dao.StudentDao;
import br.edu.ifpi.entities.Student;
import br.edu.ifpi.util.SceneNavigator;

public class LoginController implements Initializable {

    private final Connection connection;

    public LoginController(Connection connection) {
        this.connection = connection;
    }

    @FXML
    private Hyperlink createAccount;

    @FXML
    private ToggleGroup grupo;

    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private RadioButton student;

    @FXML
    private RadioButton teacher;

    @FXML
    private TextField username;

    @FXML
    void toLogin(ActionEvent event) {
        String username = this.username.getText();
        String password = this.password.getText();
        Boolean isStudent = this.student.isSelected();

        if (isStudent) {
            StudentDao studentDao = new StudentDao(this.connection);
            Student student = studentDao.login(username, password);

            if (student != null) {
                try {
                    SceneNavigator sceneNavigator = new SceneNavigator();
                    Stage stage = (Stage) login.getScene().getWindow();
                    StudentHomeController studentHomeController = new StudentHomeController(this.connection, student);
                    sceneNavigator.navigateTo(Routes.studentHome, stage, studentHomeController, true);
                } catch (Exception e) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setHeaderText("Erro ao fazer login");
                    alert.setContentText("Não foi possível fazer login");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Erro ao fazer login");
                alert.setContentText("Usuário ou senha incorretos");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void newAccount(ActionEvent event) {
        try {
            SceneNavigator sceneNavigator = new SceneNavigator();
            Stage stage = (Stage) createAccount.getScene().getWindow();
            RegisterController registerController = new RegisterController(this.connection);
            sceneNavigator.navigateTo(Routes.register, stage, registerController, false);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
