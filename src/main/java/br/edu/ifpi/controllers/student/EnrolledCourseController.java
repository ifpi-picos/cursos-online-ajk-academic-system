package br.edu.ifpi.controllers.student;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifpi.entities.Course;
import br.edu.ifpi.entities.StudentCourse;
import br.edu.ifpi.entities.enums.EnrollmentStatus;
import br.edu.ifpi.util.AlertMessage;
import br.edu.ifpi.util.Preferences;

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

public class EnrolledCourseController extends StudentController {

    private ObservableList<Course> observableListCourse;

    public EnrolledCourseController(StudentController studentController) {
        super(
                studentController.connection,
                studentController.sceneNavigator,
                studentController.student,
                studentController.stage,
                studentController.loginController,
                studentController.courseDao,
                studentController.studentCourseDao,
                studentController.studentDao);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("Olá, " + student.getName());
        loadEnrolledCourses();

        tableEnrolledCourses.setItems(observableListCourse);

        isDarkMode = Preferences.isDarkMode();

        if (isDarkMode) {
            setDarkMode();
        } else {
            setLightMode();
        }
    }

    @FXML
    void cancelRegistration(ActionEvent event) {
        Course course = tableEnrolledCourses.getSelectionModel().getSelectedItem();
        StudentCourse studentCourse = studentCourseDao.select(student.getId(), course.getId());
        studentCourse.setEnrollmentStatus(EnrollmentStatus.CANCELED);

        int row = super.studentCourseDao.update(studentCourse);
        if (row > 0) {
            AlertMessage.show("Sucesso", "", "Matrícula cancelada com sucesso", AlertType.INFORMATION);

            loadEnrolledCourses();
            tableEnrolledCourses.setItems(observableListCourse);
        } else {
            AlertMessage.show("Erro", "", "Erro ao cancelar matrícula", AlertType.ERROR);
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
}