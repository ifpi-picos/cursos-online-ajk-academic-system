package br.edu.ifpi.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import br.edu.ifpi.config.Routes;
import br.edu.ifpi.data.dao.StudentDao;
import br.edu.ifpi.data.dao.TeacherDao;
import br.edu.ifpi.entities.Student;
import br.edu.ifpi.entities.Teacher;
import br.edu.ifpi.util.SceneNavigator;
import javafx.fxml.Initializable;

public class RegisterController implements Initializable {

    private final Connection connection;

    public RegisterController(Connection connection) {
        this.connection = connection;
    }

    @FXML
    private Button back;

    @FXML
    private Button createAccount;

    @FXML
    private TextField email;

    @FXML
    private ToggleGroup grupo;

    @FXML
    private TextField name;

    @FXML
    private PasswordField password;

    @FXML
    private RadioButton student;

    @FXML
    private RadioButton teacher;

    @FXML
    void backToLogin(ActionEvent event) {
        try {
            URL login = getClass().getResource(Routes.login);
            LoginController loginController = new LoginController(this.connection);
            SceneNavigator.navigateTo(login, back, loginController);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void newAccount(ActionEvent event) {
        StudentDao studentDao = new StudentDao(this.connection);
        TeacherDao teacherDao = new TeacherDao(this.connection);

        String name = this.name.getText();
        String email = this.email.getText();
        String password = this.password.getText();
        Boolean isStudent = this.student.isSelected();

        // verificar se os campos estão vazios
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            System.out.println("Preencha todos os campos!");
        } else {
            if (isStudent) {
                Student user = new Student(name, email, password);
                studentDao.insert(user);
                System.out.println("Usuário criado com sucesso!");
            } else {
                Teacher user = new Teacher(name, email, password);
                System.out.println("Usuário criado com sucesso!");
                teacherDao.insert(user);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // limpar o texto dos campos
        this.name.setText("");
        
    }

}