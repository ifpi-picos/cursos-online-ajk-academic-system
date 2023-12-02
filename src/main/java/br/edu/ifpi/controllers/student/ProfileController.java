package br.edu.ifpi.controllers.student;

import java.sql.Connection;

import br.edu.ifpi.controllers.LoginController;
import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.StudentCourseDao;
import br.edu.ifpi.entities.User;
import br.edu.ifpi.util.SceneNavigator;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ProfileController extends StudentController {

    public ProfileController(
            Connection connection,
            SceneNavigator sceneNavigator,
            User student,
            Stage stage,
            LoginController loginController,
            CourseDao courseDao,
            StudentCourseDao studentCourseDao) {
        super(connection, sceneNavigator, student, stage, loginController, courseDao, studentCourseDao);
    }

    @FXML
    private TextField email;

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private TextField password;

    @FXML
    void informationUpdate(ActionEvent event) {

    }

}