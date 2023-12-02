package br.edu.ifpi.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import br.edu.ifpi.config.Routes;
import br.edu.ifpi.data.dao.StudentDao;
import br.edu.ifpi.data.dao.TeacherDao;
import br.edu.ifpi.entities.Student;
import br.edu.ifpi.entities.Teacher;
import br.edu.ifpi.entities.enums.StudentStatus;
import br.edu.ifpi.entities.enums.TeacherStatus;
import br.edu.ifpi.util.AlertMessage;
import br.edu.ifpi.util.SceneNavigator;
import javafx.fxml.Initializable;

public class RegisterController implements Initializable {

    private final Connection connection;
    private final LoginController loginController;
    private SceneNavigator sceneNavigator;
    private Stage stage;

    public RegisterController(
            Connection connection,
            Stage stage,
            SceneNavigator sceneNavigator,
            LoginController loginController) {
        this.connection = connection;
        this.stage = stage;
        this.sceneNavigator = sceneNavigator;
        this.loginController = loginController;
    }

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
    void toGoBack(ActionEvent event) {
        try {
            sceneNavigator.navigateTo(Routes.login, this.stage, loginController);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void createAccount(ActionEvent event) {
        StudentDao studentDao = new StudentDao(this.connection);
        TeacherDao teacherDao = new TeacherDao(this.connection);

        String name = this.name.getText();
        String email = this.email.getText();
        String password = this.password.getText();
        Boolean isStudent = this.student.isSelected();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            AlertMessage.show("Erro ao cadastrar", "", "Preencha todos os campos!", AlertType.ERROR);
        } else if (!isValidEmail(email)) {
            AlertMessage.show("Erro ao cadastrar", "", "Email inválido!", AlertType.ERROR);
        } else if (isStudent) {
            Student user = new Student(name, email, password, StudentStatus.ACTIVE);
            int row = studentDao.insert(user);
            if (row > 0) {
                AlertMessage.show("Sucesso", "", "Usuário criado com sucesso!", AlertType.INFORMATION);
            } else {
                AlertMessage.show("Erro ao cadastrar", "", "Email já cadastrado!", AlertType.ERROR);
            }
            sceneNavigator.navigateTo(Routes.login, this.stage, loginController);
        } else {
            Teacher user = new Teacher(name, email, password, TeacherStatus.ACTIVE);
            teacherDao.insert(user);
            AlertMessage.show("Sucesso", "", "Usuário criado com sucesso!", AlertType.INFORMATION);
            sceneNavigator.navigateTo(Routes.login, this.stage, loginController);
        }
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

        return pattern.matcher(email).matches();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
