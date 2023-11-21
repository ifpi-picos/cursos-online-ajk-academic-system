package br.edu.ifpi.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertMessage {
    public static void show(String title, String content, AlertType type) {
        Alert alert = new Alert(type);
        alert.setHeaderText(content);
        alert.setTitle(title);
        alert.showAndWait();
        alert.setContentText(null);
    }
}