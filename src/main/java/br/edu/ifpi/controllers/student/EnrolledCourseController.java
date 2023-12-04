package br.edu.ifpi.controllers.student;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifpi.config.Routes;
import br.edu.ifpi.controllers.LoginController;
import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.StudentCourseDao;
import br.edu.ifpi.data.dao.StudentDao;
import br.edu.ifpi.entities.Course;
import br.edu.ifpi.entities.StudentCourse;
import br.edu.ifpi.entities.User;
import br.edu.ifpi.entities.enums.EnrollmentStatus;
import br.edu.ifpi.util.AlertMessage;
import br.edu.ifpi.util.SceneNavigator;
import br.edu.ifpi.util.prefs.PreferencesUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
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
            StudentCourseDao studentCourseDao,
            StudentDao studentDao) {

        super(connection, sceneNavigator, student, stage, loginController, courseDao, studentCourseDao, studentDao);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("Olá, " + student.getName());
        loadEnrolledCourses();

        tableEnrolledCourses.setItems(observableListCourse);

        isDarkMode = PreferencesUtil.isDarkMode();

        if (isDarkMode) {
            setDarkMode();
        } else {
            setLightMode();
        }
    }

    //darkmode

    @FXML
    private Button btnMode;

    @FXML
    private Button exit;

    @FXML
    private ImageView imgMode;

    @FXML
    private BorderPane parent;

    private boolean isDarkMode = false;

    public void setMode(ActionEvent event) {
        isDarkMode = !isDarkMode;
        if(isDarkMode) {
            setDarkMode();
        } else {
            setLightMode();
        }

        PreferencesUtil.setDarkMode(isDarkMode);
    }

    private void setDarkMode() {
        parent.getStylesheets().remove(getClass().getResource(Routes.lightMode).toExternalForm());
        parent.getStylesheets().add(getClass().getResource(Routes.darkMode).toExternalForm());
        Image image = new Image(getClass().getResourceAsStream(Routes.imgSun));
        imgMode.setImage(image);
    }

    private void setLightMode() {
        parent.getStylesheets().remove(getClass().getResource(Routes.darkMode).toExternalForm());
        parent.getStylesheets().add(getClass().getResource(Routes.lightMode).toExternalForm());
        Image image = new Image(getClass().getResourceAsStream(Routes.imgMoon));
        imgMode.setImage(image);
    }
}