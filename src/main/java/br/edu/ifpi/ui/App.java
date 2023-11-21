package br.edu.ifpi.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;

import br.edu.ifpi.config.Routes;
import br.edu.ifpi.controllers.LoginController;
import br.edu.ifpi.data.dao.DatabaseConnection;

public class App extends Application {

    private static final Connection connection = DatabaseConnection.getConnection();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        boolean createdTables = DatabaseConnection.createTable(connection);
        System.out.println(connection != null ? "Conexão estabelecida!" : "Erro ao conectar ao bd.");
        System.out.println(createdTables ? "Tabelas criadas com sucesso!" : "Erro ao criar tabelas.");
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Routes.login));

            // Defina o controlador para o FXMLLoader
            loader.setController(new LoginController(connection));

            Parent root = loader.load();
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Sistema Acadêmico");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        System.out.println("Fechando conexão com o banco de dados...");
        DatabaseConnection.closeConnection();
    }
}