package br.edu.ifpi.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.net.URL;

import java.io.IOException;

public class SceneNavigator {

    public static void navigateTo(URL fxmlPath, Button button, Object controller, Boolean fullScreen) {
        FXMLLoader loader = new FXMLLoader(fxmlPath);
        loader.setController(controller);

        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) button.getScene().getWindow();
            stage.setScene(scene);
            if (fullScreen) {
                stage.setMaximized(true);
                stage.setResizable(true);
            }
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
