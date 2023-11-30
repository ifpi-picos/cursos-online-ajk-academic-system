package br.edu.ifpi.controllers.student;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifpi.data.dao.StudentCourseDao;
import br.edu.ifpi.entities.Course;
import br.edu.ifpi.entities.Student;
import br.edu.ifpi.entities.StudentCourse;
import br.edu.ifpi.entities.enums.EnrollmentStatus;
import br.edu.ifpi.entities.enums.StudentStatus;
import br.edu.ifpi.util.SceneNavigator;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CompletedCourseController extends StudentHomeController {

    private final StudentCourseDao studentCourseDao;
    private ObservableList<StudentCourse> observableListCourse;

    public CompletedCourseController(Connection connection, SceneNavigator sceneNavigator, Student student,
            Stage stage) {
        super(connection, sceneNavigator, student, stage);
        this.studentCourseDao = new StudentCourseDao(connection);
        // TODO Auto-generated constructor stub
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
        List<StudentCourse> studentCourses = studentCourseDao.selectAll("status != " + "'" + EnrollmentStatus.PENDING + "'");

        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudent().getName()));
        course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourse().getName()));
        grade.setCellValueFactory(new PropertyValueFactory<>("grade"));
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
