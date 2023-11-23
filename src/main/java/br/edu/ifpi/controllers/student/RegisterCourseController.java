package br.edu.ifpi.controllers.student;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifpi.config.Routes;
import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.StudentCourseDao;
import br.edu.ifpi.entities.Course;
import br.edu.ifpi.entities.Student;
import br.edu.ifpi.entities.StudentCourse;
import br.edu.ifpi.entities.enums.StatusCourse;
import br.edu.ifpi.util.AlertMessage;
import br.edu.ifpi.util.SceneNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RegisterCourseController extends StudentHomeController {

    private final CourseDao coursesDao;
    private final StudentCourseDao studentCourseDao;
    private ObservableList<Course> observableListCourse;

    public RegisterCourseController(
            Connection connection,
            SceneNavigator sceneNavigator,
            Stage stage,
            Student student) {
        super(connection, sceneNavigator, student, stage);
        this.coursesDao = new CourseDao(connection);
        this.studentCourseDao = new StudentCourseDao(connection);
    }

    @FXML
    private Button exit;

    @FXML
    private TableColumn<Course, Integer> id;

    @FXML
    private TableColumn<Course, String> name;

    @FXML
    private Button registerCourse;

    @FXML
    private TableView<Course> tableRegister;

    @FXML
    private TableColumn<Course, String> teacher;

    @FXML
    private Text username;

    @FXML
    private TableColumn<Course, Integer> workload;

    @FXML
    void completedCourses(ActionEvent event) {

    }

    @FXML
    void registerCourse(ActionEvent event) {
        Course selectedItem = tableRegister.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            StudentCourse studentCourse = new StudentCourse(
                    super.student,
                    selectedItem,
                    null,
                    selectedItem.getStatus());

            studentCourseDao.insert(studentCourse);
            AlertMessage.show("Sucesso", "Sucesso", "Matrícula realizada com sucesso", AlertType.INFORMATION);

            // atualiza a tabela de cursos disponíveis
            RegisterCourseController registerCourseController = new RegisterCourseController(
                    connection,
                    sceneNavigator,
                    stage,
                    student);
            sceneNavigator.navigateTo(Routes.registerCourse, this.stage, registerCourseController);
        } else {
            AlertMessage.show("Erro", "Erro", "Selecione um curso para se matricular", AlertType.ERROR);
        }

    }

    public void selectCourseTableItem(Course course) {
        System.out.println("Curso selecionado: " + course.getName());

    }

    public void loadTableCourse() {
        // cursos disponíveis para matrícula
        List<Course> courses = coursesDao.selectAll("status = '" + StatusCourse.OPEN + "'");
        // cursos que o aluno já está matriculado
        List<StudentCourse> coursesStudent = studentCourseDao.selectAll("student_id = " + super.student.getId());
        // remove os cursos que o aluno já está matriculado
        courses.removeIf(course -> coursesStudent.stream()
                .anyMatch(studentCourse -> studentCourse.getCourse().getId() == course.getId()));

        // define as colunas da tabela e os dados que serão exibidos
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        teacher.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTeacher().getName()));
        workload.setCellValueFactory(new PropertyValueFactory<>("workload"));
        observableListCourse = FXCollections.observableArrayList(courses);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTableCourse();

        tableRegister.setItems(observableListCourse);
        tableRegister.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectCourseTableItem(newValue));

    }
}
