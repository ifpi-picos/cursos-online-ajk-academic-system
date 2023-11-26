package br.edu.ifpi.util;

import br.edu.ifpi.config.Routes;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AlertMessage {
    public static void show(String title, String header, String content, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        Image iconImage = new Image(AlertMessage.class.getResourceAsStream(Routes.iconAlert));

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(iconImage);

        stage.setTitle(title);
        stage.getIcons().add(iconImage);

        alert.getDialogPane().setGraphic(null);

        alert.showAndWait();
    }
}