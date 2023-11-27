package br.edu.ifpi.controllers.teacher;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.StudentCourseDao;
import br.edu.ifpi.entities.Course;
import br.edu.ifpi.entities.StudentCourse;
import br.edu.ifpi.entities.Teacher;
import br.edu.ifpi.entities.enums.EnrollmentStatus;
import br.edu.ifpi.util.SceneNavigator;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TeacherOpenCourseController extends TeacherHomeController {

    private ObservableList<StudentCourse> observableListStudentCourse;
    private final StudentCourseDao studentCourseDao;
    private final Course course;

    public TeacherOpenCourseController(Connection connection, SceneNavigator sceneNavigator, Teacher teacher,
            Stage stage, Course course) {

        super(connection, sceneNavigator, teacher, stage);
        studentCourseDao = new StudentCourseDao(connection);
        this.course = course;

    }

    @FXML
    private TableColumn<StudentCourse, String> email;

    @FXML
    private TableColumn<StudentCourse, Double> grade;

    @FXML
    private TextField gradeValue;

    @FXML
    private TableColumn<StudentCourse, Integer> id;

    @FXML
    private TableColumn<StudentCourse, String> name;

    @FXML
    private Text nameCourse;

    @FXML
    private TableColumn<StudentCourse, String> status;

    @FXML
    private TableView<StudentCourse> tableCourse;

    @FXML
    private Text username;

    @FXML
    void backCourses(ActionEvent event) {

    }

    @FXML
    void giveGrade(ActionEvent event) {

    }

    public void loadTableCourse() {
        List<StudentCourse> studentCourses = studentCourseDao.selectAll("course_id = " + course.getId());

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        grade.setCellValueFactory(new PropertyValueFactory<>("finalGrade"));
        status.setCellValueFactory(cellData -> new SimpleStringProperty(getStatus(cellData.getValue())));

        observableListStudentCourse = FXCollections.observableArrayList(studentCourses);
    }

    public String getStatus(StudentCourse studentCourse) {
        if (studentCourse.getEnrollmentStatus() == EnrollmentStatus.PENDING) {
            return "Pendente";
        } else if (studentCourse.getEnrollmentStatus() == EnrollmentStatus.APPROVED) {
            return "Aprovado";
        } else if (studentCourse.getEnrollmentStatus() == EnrollmentStatus.CANCELED) {
            return "Cancelado";
        } else if (studentCourse.getEnrollmentStatus() == EnrollmentStatus.REPROVED) {
            return "Reprovado";
        } else {
            return "Sem status";
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("Olá, " + teacher.getName());
        nameCourse.setText(this.course.getName());

    }
}
