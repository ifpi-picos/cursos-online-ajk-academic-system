package br.edu.ifpi.controllers.student;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifpi.controllers.LoginController;
import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.StudentCourseDao;
import br.edu.ifpi.entities.Student;
import br.edu.ifpi.entities.StudentCourse;
import br.edu.ifpi.entities.enums.EnrollmentStatus;
import br.edu.ifpi.util.SceneNavigator;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CompletedCourseController extends StudentController {

    private ObservableList<StudentCourse> observableListCourse;

    public CompletedCourseController(
            Connection connection,
            SceneNavigator sceneNavigator,
            Student student,
            Stage stage,
            LoginController loginController,
            CourseDao courseDao,
            StudentCourseDao studentCourseDao) {

        super(connection, sceneNavigator, student, stage, loginController, courseDao, studentCourseDao);
    }

    @FXML
    private Text username;

    @FXML
    private TableColumn<StudentCourse, String> course;

    @FXML
    private TableColumn<StudentCourse, Double> grade;

    @FXML
    private TableColumn<StudentCourse, String> name;

    @FXML
    private TableColumn<StudentCourse, String> status;

    @FXML
    private TableView<StudentCourse> tableCompletedCourses;

    public void loadCompletedCourses() {
        final String[] conditions = {
                "student_id = " + student.getId(),
                "status = '" + EnrollmentStatus.APPROVED.toString() + "'"
        };
        
        List<StudentCourse> studentCourses = super.studentCourseDao.selectAll(conditions);

        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudent().getName()));
        course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourse().getName()));
        grade.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getFinalGrade()).asObject());
        status.setCellValueFactory(cellData -> new SimpleStringProperty(getStatus(cellData.getValue())));
        observableListCourse = FXCollections.observableArrayList(studentCourses);

    }

    public String getStatus(StudentCourse studentCourse) {
        if (studentCourse.getEnrollmentStatus() == EnrollmentStatus.APPROVED) {
            return "Aprovado";
        } else if (studentCourse.getEnrollmentStatus() == EnrollmentStatus.REPROVED) {
            return "Reprovado";
        } else if (studentCourse.getEnrollmentStatus() == EnrollmentStatus.CANCELED) {
            return "Cancelado";
        } else {
            return "Pendente";
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("Olá, " + student.getName());
        loadCompletedCourses();

        tableCompletedCourses.setItems(observableListCourse);

    }

}
