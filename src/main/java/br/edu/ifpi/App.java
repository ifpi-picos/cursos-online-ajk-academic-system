package br.edu.ifpi;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.sql.Connection;

import br.edu.ifpi.dao.DatabaseConnection;

public class App extends Application {
    public static void main(String[] args) {
        // Conectando ao banco de dados
        Connection connection = DatabaseConnection.getConnection();
        // Verificando se a conexão foi bem sucedida
        System.out.println(connection != null ? "Conexão bem sucedida!" : "Conexão falhou!");

        // Iniciando a aplicação
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Criando um botão
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Imprimindo no console
                System.out.println("Hello World!");
            }
        });

        // Criando um StackPane
        StackPane root = new StackPane();
        root.getChildren().add(btn);

        // Criando uma Scene fullscreen
        Scene scene = new Scene(root, 1280, 720);

        // Configurando o Stage
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}