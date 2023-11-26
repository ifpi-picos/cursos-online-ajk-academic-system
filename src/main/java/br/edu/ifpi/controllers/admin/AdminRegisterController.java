package br.edu.ifpi.controllers.admin;

import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import java.net.URL;

import br.edu.ifpi.config.Routes;
import br.edu.ifpi.controllers.RegisterController;
import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.TeacherDao;
import br.edu.ifpi.entities.Admin;
import br.edu.ifpi.entities.Course;
import br.edu.ifpi.entities.Teacher;
import br.edu.ifpi.entities.enums.CourseStatus;
import br.edu.ifpi.util.AlertMessage;
import br.edu.ifpi.util.SceneNavigator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdminRegisterController extends AdminHomeController {

    private final TeacherDao teacherDao;
    private final CourseDao courseDao;
    private ObservableList<Teacher> observableListTeacher;

    // editar curso
    private Course course;

    public AdminRegisterController(Connection connection, SceneNavigator sceneNavigator, Admin admin, Stage stage,
            Course course) {
        super(connection, sceneNavigator, admin, stage);
        teacherDao = new TeacherDao(connection);
        courseDao = new CourseDao(connection);
        this.course = course;
    }

    @FXML
    private Text title;

    @FXML
    private TextField courseName;

    @FXML
    private TableColumn<Teacher, Integer> id;

    @FXML
    private TableView<Teacher> tableTeacher;

    @FXML
    private TableColumn<Teacher, String> teacherName;

    @FXML
    private Text username;

    @FXML
    private TextField workload;

    @FXML
    void confirm(ActionEvent event) {
        String name = courseName.getText();
        int workload = this.workload.getText().isEmpty() ? 0 : Integer.parseInt(this.workload.getText());
        Teacher teacher = tableTeacher.getSelectionModel().getSelectedItem();

        if (name.isEmpty() || workload <= 0) {
            AlertMessage.show("Erro", "Erro!", "Preencha todos os campos!", AlertType.ERROR);
            return;
        } else if (teacher == null) {
            AlertMessage.show("Erro", "Erro!", "Selecione um professor!", AlertType.ERROR);
            return;
        }

        if (this.course != null) {
            this.course.setName(name);
            this.course.setWorkload(workload);
            this.course.setTeacher(teacher);

            int row = courseDao.update(this.course);

            if (row > 0) {
                AlertMessage.show("Sucesso", "Sucesso!", "Curso atualizado com sucesso!", AlertType.INFORMATION);
            } else {
                AlertMessage.show("Erro", "Erro!", "Erro ao atualizar curso!", AlertType.ERROR);
            }

            AdminSeeCoursesController adminSeeCoursesController = new AdminSeeCoursesController(connection,
                    sceneNavigator, admin, stage);

            sceneNavigator.navigateTo(Routes.adminSeeCourses, stage, adminSeeCoursesController);

            return;
        }

        Course course = new Course(name, CourseStatus.OPEN, workload, teacher);
        int row = courseDao.insert(course);

        if (row > 0) {
            AlertMessage.show("Sucesso", "Sucesso!", "Curso cadastrado com sucesso!", AlertType.INFORMATION);
        } else {
            AlertMessage.show("Erro", "Erro!", "Erro ao cadastrar curso!", AlertType.ERROR);
        }
    }

    @FXML
    void usernameButton(MouseEvent event) {

    }

    public void loadTeachers() {
        List<Teacher> teachers = teacherDao.selectAll("status = 'ACTIVE'");

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        teacherName.setCellValueFactory(new PropertyValueFactory<>("name"));

        observableListTeacher = FXCollections.observableArrayList(teachers);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("Olá, " + admin.getName());

        loadTeachers();
        tableTeacher.setItems(observableListTeacher);

        if (course != null) {
            title.setText("Editar curso");
            courseName.setText(course.getName());
            workload.setText(String.valueOf(course.getWorkload()));
            tableTeacher.getSelectionModel().select(course.getTeacher());
        }
    }
}
