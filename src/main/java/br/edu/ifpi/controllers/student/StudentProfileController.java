package br.edu.ifpi.controllers.student;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import br.edu.ifpi.controllers.LoginController;
import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.StudentCourseDao;
import br.edu.ifpi.data.dao.StudentDao;
import br.edu.ifpi.entities.Student;
import br.edu.ifpi.util.AlertMessage;
import br.edu.ifpi.util.SceneNavigator;

import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class StudentProfileController extends StudentController {

    public StudentProfileController(Connection connection, SceneNavigator sceneNavigator, Student student, Stage stage,
            LoginController loginController, CourseDao courseDao, StudentCourseDao studentCourseDao, StudentDao studentDao) {
        super(connection, sceneNavigator, student, stage, loginController, courseDao, studentCourseDao, studentDao);
    }

    @FXML
    private TextField email;

    @FXML
    private Button exit;

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private TextField password;

    @FXML
    private BorderPane profileInformation;

    @FXML
    private Text username;

    @FXML
    void informationUpdate(ActionEvent event) {
        super.student.setPassword(password.getText());
        super.student.setName(name.getText());
        super.student.setId(Integer.parseInt(id.getText()));

        int row = super.studentDao.update(super.student);

        if (row > 0) {
            AlertMessage.show("Sucesso", "Informações atualizadas com sucesso!", "", AlertType.INFORMATION);
        } else {
            AlertMessage.show("Erro", "Não foi possível atualizar as informações!", "", AlertType.ERROR);
        }
    }

    public void loadProfileStudent() {
        email.setText(super.student.getEmail());
        password.setText(super.student.getPassword());
        name.setText(super.student.getName());
        id.setText(String.valueOf(super.student.getId()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("Olá, " + student.getName());
        loadProfileStudent();
    }
}
