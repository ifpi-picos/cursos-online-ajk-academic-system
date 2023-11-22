package br.edu.ifpi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;

import br.edu.ifpi.config.Routes;
import br.edu.ifpi.controllers.LoginController;
import br.edu.ifpi.data.dao.DatabaseConnection;
import br.edu.ifpi.util.SceneNavigator;

public class App extends Application {

    private Connection connection;
    private Stage stage;
    private SceneNavigator sceneNavigator;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        connection = DatabaseConnection.getConnection();
        sceneNavigator = new SceneNavigator();

        boolean createdTables = DatabaseConnection.createTable(connection);

        System.out.println(connection != null ? "-conexão estabelecida" : "-erro ao conectar ao bd");
        System.out.println(createdTables ? "-tabelas criadas com sucesso" : "-erro ao criar tabelas");
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Routes.login));
            loader.setController(new LoginController(connection, stage, sceneNavigator));

            Parent root = loader.load();
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Sistema Acadêmico");
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