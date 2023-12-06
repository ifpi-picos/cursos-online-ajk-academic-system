package br.edu.ifpi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.sql.Connection;

import br.edu.ifpi.configs.Routes;
import br.edu.ifpi.controllers.LoginController;
import br.edu.ifpi.data.dao.AdminDao;
import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.DatabaseConnection;
import br.edu.ifpi.data.dao.StudentCourseDao;
import br.edu.ifpi.data.dao.StudentDao;
import br.edu.ifpi.data.dao.TeacherDao;
import br.edu.ifpi.util.SceneNavigator;

public class App extends Application {

    private Connection connection;

    private AdminDao adminDao;
    private TeacherDao teacherDao;
    private StudentDao studentDao;
    private CourseDao courseDao;
    private StudentCourseDao studentCourseDao;

    private SceneNavigator sceneNavigator;
    private LoginController loginController;
    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        // fazendo a conexão com o banco de dados
        connection = DatabaseConnection.getConnection();
        boolean createdTables = DatabaseConnection.createTable(connection);

        // instanciando os DAOs
        adminDao = new AdminDao(connection);
        teacherDao = new TeacherDao(connection);
        studentDao = new StudentDao(connection);
        courseDao = new CourseDao(connection);
        studentCourseDao = new StudentCourseDao(connection);

        // instanciando a classe que gerencia as cenas
        sceneNavigator = new SceneNavigator();

        // testando a conexão com o banco de dados
        System.out.println(connection != null ? "-conexão estabelecida" : "-erro ao conectar ao bd");
        System.out.println(createdTables ? "-tabelas criadas com sucesso" : "-erro ao criar tabelas");
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        try {
            Image icon = new Image(getClass().getResourceAsStream(Routes.icon));
            primaryStage.getIcons().add(icon);

            FXMLLoader loader = new FXMLLoader(getClass().getResource(Routes.login));

            loginController = new LoginController(
                    connection,
                    adminDao,
                    teacherDao,
                    studentDao,
                    courseDao,
                    studentCourseDao,
                    sceneNavigator,
                    stage);

            loader.setController(loginController);

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