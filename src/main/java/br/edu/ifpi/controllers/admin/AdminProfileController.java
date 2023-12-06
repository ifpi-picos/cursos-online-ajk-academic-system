package br.edu.ifpi.controllers.admin;

import java.util.ResourceBundle;

import br.edu.ifpi.util.AlertMessage;
import br.edu.ifpi.util.Preferences;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

public class AdminProfileController extends AdminController {

    public AdminProfileController(AdminController adminController) {
        super(
                adminController.connection,
                adminController.sceneNavigator,
                adminController.admin,
                adminController.stage,
                adminController.courseDao,
                adminController.teacherDao,
                adminController.studentDao,
                adminController.adminDao,
                adminController.loginController);
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
