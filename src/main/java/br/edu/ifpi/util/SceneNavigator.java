package br.edu.ifpi.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneNavigator {

    public void navigateTo(String route, Stage stage, Object controller) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(route));
        loader.setController(controller);

        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}