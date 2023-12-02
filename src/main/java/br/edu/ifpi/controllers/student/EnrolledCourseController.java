package br.edu.ifpi.controllers.student;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifpi.controllers.LoginController;
import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.StudentCourseDao;
import br.edu.ifpi.entities.Course;
import br.edu.ifpi.entities.StudentCourse;
import br.edu.ifpi.entities.User;
import br.edu.ifpi.entities.enums.EnrollmentStatus;
import br.edu.ifpi.util.AlertMessage;
import br.edu.ifpi.util.SceneNavigator;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EnrolledCourseController extends StudentController {

    private ObservableList<Course> observableListCourse;

    public EnrolledCourseController(
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
    private Text username;
    @FXML
    private TableView<Course> tableEnrolledCourses;

    @FXML
    private TableColumn<Course, Integer> id;

    @FXML
    private TableColumn<Course, String> name;

    @FXML
    private TableColumn<Course, String> teacher;

    @FXML
    private TableColumn<Course, Integer> workload;

    @FXML
    void cancelRegistration(ActionEvent event) {
        Course course = tableEnrolledCourses.getSelectionModel().getSelectedItem();
        StudentCourse studentCourse = studentCourseDao.select(student.getId(), course.getId());
        studentCourse.setEnrollmentStatus(EnrollmentStatus.CANCELED);

        int row = super.studentCourseDao.update(studentCourse);
        if (row > 0) {
            AlertMessage.show("Sucesso", "Sucesso", "Matrícula cancelada com sucesso", AlertType.INFORMATION);

            loadEnrolledCourses();
            tableEnrolledCourses.setItems(observableListCourse);
        } else {
            AlertMessage.show("Erro", "Erro", "Erro ao cancelar matrícula", AlertType.ERROR);
        }
    }

    @FXML
    public void loadEnrolledCourses() {
        String condition = "student_id = " + student.getId() + " AND status = '" + EnrollmentStatus.PENDING + "'";
        List<StudentCourse> studentCourses = super.studentCourseDao.selectAll(condition);
        List<Course> courses = new ArrayList<>();

        for (StudentCourse studentCourse : studentCourses) {
            Course course = studentCourse.getCourse();
            courses.add(course);
        }

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        teacher.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTeacher().getName()));
        workload.setCellValueFactory(new PropertyValueFactory<>("workload"));
        observableListCourse = FXCollections.observableArrayList(courses);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("Olá, " + student.getName());
        loadEnrolledCourses();

        tableEnrolledCourses.setItems(observableListCourse);
    }
}