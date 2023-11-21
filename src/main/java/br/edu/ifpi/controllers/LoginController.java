package br.edu.ifpi.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import br.edu.ifpi.config.Routes;
import br.edu.ifpi.data.dao.StudentDao;
import br.edu.ifpi.entities.Student;
import br.edu.ifpi.util.AlertMessage;

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
                System.out.println("Login realizado com sucesso");
            } else {
                AlertMessage.show("Erro", "Usuário ou senha incorretos", AlertType.WARNING);
            }
        }
    }

    @FXML
    void newAccount(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Routes.register));
            loader.setController(new RegisterController(connection));
            Parent root = loader.load();
            this.createAccount.getScene().setRoot(root);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void redirectTeacher() {
        System.out.println("Redirecionar para a tela do professor");
    }

    public void redirectStudent() {
        System.out.println("Redirecionar para a tela do aluno");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.student.setSelected(true);
    }

}
