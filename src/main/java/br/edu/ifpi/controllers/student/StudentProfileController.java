package br.edu.ifpi.controllers.student;

import java.util.ResourceBundle;

import br.edu.ifpi.util.AlertMessage;
import br.edu.ifpi.util.Preferences;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

public class StudentProfileController extends StudentController {

    public StudentProfileController(StudentController studentController) {
        super(
                studentController.connection,
                studentController.sceneNavigator,
                studentController.student,
                studentController.stage,
                studentController.loginController,
                studentController.courseDao,
                studentController.studentCourseDao,
                studentController.studentDao);
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

    @Override
    public void initialize(java.net.URL location, ResourceBundle resources) {
        username.setText("Olá, " + student.getName());
        name.setText(student.getName());
        email.setText(student.getEmail());
        id.setText(String.valueOf(student.getId()));
        password.setText(student.getPassword());

        isDarkMode = Preferences.isDarkMode();

        if (isDarkMode) {
            setDarkMode();
        } else {
            setLightMode();
        }
    }

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
}
