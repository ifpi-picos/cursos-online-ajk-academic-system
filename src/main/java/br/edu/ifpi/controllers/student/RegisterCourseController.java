package br.edu.ifpi.controllers.student;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifpi.entities.Course;
import br.edu.ifpi.entities.Student;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RegisterCourseController extends StudentHomeController {

    public RegisterCourseController(Connection connection, SceneNavigator sceneNavigator, Stage stage,
            Student student) {
        super(connection, sceneNavigator, stage, student);
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
    private TableColumn<Course, Integer> teacher_id;

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
            System.out.println("Curso selecionado: " + selectedItem.getName());
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Nenhum curso selecionado");
            alert.setContentText("Selecione um curso para se matricular");
            alert.showAndWait();
        }

    }

    private List<Course> courses = new ArrayList<>();

    private ObservableList<Course> observableListCourse;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTableCourse();
        tableRegister.setItems(observableListCourse);

        tableRegister.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> selectCourseTableItem(newValue));

    }

    public void loadTableCourse() {

        Course c1 = new Course(1, "Informática", null, 40, 1);
        Course c2 = new Course(2, "Informática", null, 40, 1);

        courses.add(c1);
        courses.add(c2);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        teacher_id.setCellValueFactory(new PropertyValueFactory<>("teacherId"));
        workload.setCellValueFactory(new PropertyValueFactory<>("workload"));
        observableListCourse = FXCollections.observableArrayList(courses);
    }

    public void selectCourseTableItem(Course course) {
        System.out.println("Curso selecionado: " + course.getName());

    }

}
