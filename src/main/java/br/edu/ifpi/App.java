package br.edu.ifpi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.sql.Connection;

import br.edu.ifpi.config.Routes;
import br.edu.ifpi.controllers.LoginController;
import br.edu.ifpi.data.dao.DatabaseConnection;

public class App extends Application {

    private Connection connection;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        connection = DatabaseConnection.getConnection();
        boolean createdTables = DatabaseConnection.createTable(connection);

        // log
        System.out.println(connection != null ? "-conexão estabelecida" : "-erro ao conectar ao bd");
        System.out.println(createdTables ? "-tabelas criadas com sucesso" : "-erro ao criar tabelas");
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Image icon = new Image(getClass().getResourceAsStream("/br/edu/ifpi/img/logo-sistema.png"));
            primaryStage.getIcons().add(icon);

            FXMLLoader loader = new FXMLLoader(getClass().getResource(Routes.login));
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
        DatabaseConnection.closeConnection();
        System.out.println("-fechando conexão com o banco de dados...");
    }
}