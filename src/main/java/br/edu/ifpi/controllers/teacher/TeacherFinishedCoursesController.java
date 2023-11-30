package br.edu.ifpi.controllers.teacher;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import br.edu.ifpi.controllers.LoginController;
import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.StudentCourseDao;
import br.edu.ifpi.entities.Course;
import br.edu.ifpi.entities.Teacher;
import br.edu.ifpi.util.SceneNavigator;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TeacherFinishedCoursesController extends TeacherController {

    private ObservableList<Course> observableListCourse;

    public TeacherFinishedCoursesController(
            Connection connection,
            SceneNavigator sceneNavigator,
            Teacher teacher,
            Stage stage,
            LoginController loginController,
            CourseDao courseDao,
            StudentCourseDao studentCourseDao) {

        super(connection, sceneNavigator, teacher, stage, loginController, courseDao, studentCourseDao);
    }

    @FXML
    private TableColumn<Course, String> name;

    @FXML
    private TableColumn<Course, String> status;

    @FXML
    private TableView<Course> tableCourse;

    @FXML
    private Text username;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("Olá, " + teacher.getName());

        tableCourse.setItems(observableListCourse);
    }

    public void seeStudents(ActionEvent event) {
    }

    public void openCourse(ActionEvent event) {

    }
}
