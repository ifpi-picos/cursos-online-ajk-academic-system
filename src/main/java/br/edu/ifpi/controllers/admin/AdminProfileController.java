package br.edu.ifpi.controllers.admin;

import java.sql.Connection;
import java.util.ResourceBundle;

import br.edu.ifpi.controllers.LoginController;
import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.StudentDao;
import br.edu.ifpi.data.dao.TeacherDao;
import br.edu.ifpi.data.dao.AdminDao;
import br.edu.ifpi.entities.Admin;
import br.edu.ifpi.util.AlertMessage;
import br.edu.ifpi.util.Preferences;
import br.edu.ifpi.util.SceneNavigator;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

public class AdminProfileController extends AdminController {

    private Admin admin;

    public AdminProfileController(
            Connection connection,
            SceneNavigator sceneNavigator,
            Admin admin,
            Stage stage,
            CourseDao courseDao,
            StudentDao studentDao,
            AdminDao adminDao,
            LoginController loginController,
            TeacherDao teacherDao) {
        super(connection, sceneNavigator, admin, stage, courseDao, teacherDao, studentDao, adminDao, loginController);

        this.admin = admin;
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
        username.setText("Olá, " + admin.getName());
        name.setText(admin.getName());
        email.setText(admin.getEmail());
        id.setText(String.valueOf(admin.getId()));
        password.setText(admin.getPassword());

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
        admin.setPassword(password);
        admin.setName(name);

        int row = adminDao.update(admin);
        if (row > 0) {
            AlertMessage.show("Sucesso", "", "Dados atualizados com sucesso", AlertType.INFORMATION);
        } else {
            AlertMessage.show("Erro", "", "Erro ao atualizar dados", AlertType.ERROR);
        }
    }

}
