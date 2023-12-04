package br.edu.ifpi.controllers.admin;

import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import java.net.URL;

import br.edu.ifpi.config.Routes;
import br.edu.ifpi.controllers.LoginController;
import br.edu.ifpi.data.dao.AdminDao;
import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.StudentDao;
import br.edu.ifpi.data.dao.TeacherDao;
import br.edu.ifpi.entities.Course;
import br.edu.ifpi.entities.Admin;
import br.edu.ifpi.entities.enums.CourseStatus;
import br.edu.ifpi.util.AlertMessage;
import br.edu.ifpi.util.Preferences;
import br.edu.ifpi.util.SceneNavigator;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminSeeCoursesController extends AdminController {

    private ObservableList<Course> observableListCourse;

    public AdminSeeCoursesController(Connection connection,
            SceneNavigator sceneNavigator,
            Admin admin,
            Stage stage,
            CourseDao courseDao,
            TeacherDao teacherDao,
            StudentDao studentDao,
            AdminDao adminDao,
            LoginController loginController) {
        super(connection, sceneNavigator, admin, stage, courseDao, teacherDao, studentDao, adminDao, loginController);
    }

    @FXML
    private TableColumn<Course, Integer> id;

    @FXML
    private TableColumn<Course, String> name;

    @FXML
    private Button registerCourse;

    @FXML
    private Button registerCourse1;

    @FXML
    private TableColumn<Course, String> status;

    @FXML
    private TableView<Course> tableCourses;

    @FXML
    private TableColumn<Course, String> teacher;

    @FXML
    private Text username;

    @FXML
    private TableColumn<Course, Integer> workload;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("Olá, " + admin.getName());

        loadTableCourse();
        tableCourses.setItems(observableListCourse);

        isDarkMode = Preferences.isDarkMode();

        if (isDarkMode) {
            setDarkMode();
        } else {
            setLightMode();
        }
    }

    @FXML
    void activate(ActionEvent event) {
        Course course = tableCourses.getSelectionModel().getSelectedItem();
        course.setStatus(CourseStatus.OPEN);

        int row = super.courseDao.update(course);

        if (row > 0) {
            AlertMessage.show("Sucesso", "", "Curso ativado com sucesso!", AlertType.INFORMATION);
            loadTableCourse();
            tableCourses.setItems(observableListCourse);
        } else {
            AlertMessage.show("Erro", "", "Erro ao ativar curso!", AlertType.ERROR);
        }
    }

    @FXML
    void disable(ActionEvent event) {
        Course course = tableCourses.getSelectionModel().getSelectedItem();
        course.setStatus(CourseStatus.CLOSED);

        int row = super.courseDao.update(course);

        if (row > 0) {
            AlertMessage.show("Sucesso", "", "Curso desativado com sucesso!", AlertType.INFORMATION);
            loadTableCourse();
            tableCourses.setItems(observableListCourse);
        } else {
            AlertMessage.show("Erro", "", "Erro ao desativar curso!", AlertType.ERROR);
        }
    }

    @FXML
    void edit(ActionEvent event) {
        Course course = tableCourses.getSelectionModel().getSelectedItem();

        if (course != null) {
            AdminRegisterController adminRegisterController = new AdminRegisterController(super.connection,
                    super.sceneNavigator, super.admin, super.stage, super.courseDao, super.teacherDao,
                    super.studentDao, super.adminDao, super.loginController, course);
            sceneNavigator.navigateTo(Routes.adminRegisterCourse, this.stage, adminRegisterController);
        } else {
            AlertMessage.show("Erro", "", "Selecione um curso!", AlertType.ERROR);
        }
    }

    public void loadTableCourse() {
        List<Course> courses = courseDao.selectAll();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        teacher.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTeacher().getName()));
        workload.setCellValueFactory(new PropertyValueFactory<>("workload"));
        status.setCellValueFactory(cellData -> new SimpleStringProperty(getStatus(cellData.getValue())));

        // ordenar por id
        courses.sort((c1, c2) -> c1.getId() - c2.getId());

        observableListCourse = FXCollections.observableArrayList(courses);
    }

    public String getStatus(Course course) {
        if (course.getStatus() == CourseStatus.OPEN) {
            return "Ativo";
        } else {
            return "Inativo";
        }
    }
}
