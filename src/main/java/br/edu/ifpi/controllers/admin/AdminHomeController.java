package br.edu.ifpi.controllers.admin;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import br.edu.ifpi.entities.Admin;
import br.edu.ifpi.util.SceneNavigator;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class AdminHomeController implements Initializable {

    protected Connection connection;
    protected SceneNavigator sceneNavigator;
    protected Admin admin;
    protected Stage stage;

    public AdminHomeController(Connection connection, SceneNavigator sceneNavigator, Admin admin, Stage stage) {
        this.connection = connection;
        this.sceneNavigator = sceneNavigator;
        this.admin = admin;
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
