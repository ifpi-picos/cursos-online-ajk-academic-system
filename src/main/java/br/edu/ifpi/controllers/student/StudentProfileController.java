package br.edu.ifpi.controllers.student;

import java.sql.Connection;
import java.util.ResourceBundle;

import br.edu.ifpi.controllers.LoginController;
import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.StudentCourseDao;
import br.edu.ifpi.data.dao.StudentDao;
import br.edu.ifpi.entities.Student;
import br.edu.ifpi.entities.User;
import br.edu.ifpi.util.AlertMessage;
import br.edu.ifpi.util.SceneNavigator;

import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

public class StudentProfileController extends StudentController {

    private Student student;

    public StudentProfileController(
            Connection connection,
            SceneNavigator sceneNavigator,
            User student,
            Stage stage,
            LoginController loginController,
            CourseDao courseDao,
            StudentDao studentDao,
            StudentCourseDao studentCourseDao) {
        super(connection, sceneNavigator, student, stage, loginController, courseDao, studentCourseDao, studentDao);

        this.student = (Student) student;
    }

    @FXML
    private Text username;

    @FXML
    private TextField email;

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private TextField password;

    @FXML
    void informationUpdate(ActionEvent event) {
        String password = this.password.getText();
        String name = this.name.getText();

        if (password.isEmpty()) {
            AlertMessage.show("Erro", "", "Preencha a senha corretamente", AlertType.ERROR);
            return;
        }
        student.setPassword(password);
        student.setName(name);

        int row = studentDao.update(student);
        if (row > 0) {
            AlertMessage.show("Sucesso", "", "Dados atualizados com sucesso", AlertType.INFORMATION);
        } else {
            AlertMessage.show("Erro", "", "Erro ao atualizar dados", AlertType.ERROR);
        }
    }

    @Override
    public void initialize(java.net.URL location, ResourceBundle resources) {
        username.setText("Olá, " + student.getName());
        name.setText(student.getName());
        email.setText(student.getEmail());
        id.setText(String.valueOf(student.getId()));
        password.setText(student.getPassword());
    }

}
