package br.edu.ifpi.controllers.teacher;

import java.sql.Connection;
import java.util.ResourceBundle;

import br.edu.ifpi.controllers.LoginController;
import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.StudentCourseDao;
import br.edu.ifpi.data.dao.TeacherDao;
import br.edu.ifpi.entities.Teacher;
import br.edu.ifpi.util.AlertMessage;
import br.edu.ifpi.util.Preferences;
import br.edu.ifpi.util.SceneNavigator;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

public class TeacherProfileController extends TeacherController {

    private Teacher teacher;

    public TeacherProfileController(
            Connection connection,
            SceneNavigator sceneNavigator,
            Teacher teacher,
            Stage stage,
            LoginController loginController,
            CourseDao courseDao,
            StudentCourseDao studentCourseDao) {
        super(connection, sceneNavigator, teacher, stage, loginController, courseDao, studentCourseDao);

        this.teacher = teacher;
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
    TeacherDao teacherDao = new TeacherDao(connection); // Initialize the teacherDao variable with the connection
    
    @FXML
    void informationUpdate(ActionEvent event) {
        String password = this.password.getText();
        String name = this.name.getText();

        if (password.isEmpty()) {
            AlertMessage.show("Erro", "", "Preencha a senha corretamente", AlertType.ERROR);
            return;
        }
        teacher.setPassword(password);
        teacher.setName(name);

        int row = teacherDao.update(teacher);
        if (row > 0) {
            AlertMessage.show("Sucesso", "", "Dados atualizados com sucesso", AlertType.INFORMATION);
        } else {
            AlertMessage.show("Erro", "", "Erro ao atualizar dados", AlertType.ERROR);
        }
    }

    @Override
    public void initialize(java.net.URL location, ResourceBundle resources) {
        username.setText("Olá, " + teacher.getName());
        name.setText(teacher.getName());
        email.setText(teacher.getEmail());
        id.setText(String.valueOf(teacher.getId()));
        password.setText(teacher.getPassword());

        isDarkMode = Preferences.isDarkMode();

        if (isDarkMode) {
            setDarkMode();
        } else {
            setLightMode();
        }
    }
}
