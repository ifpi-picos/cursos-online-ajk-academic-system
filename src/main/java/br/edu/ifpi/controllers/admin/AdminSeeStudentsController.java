package br.edu.ifpi.controllers.admin;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifpi.controllers.LoginController;
import br.edu.ifpi.data.dao.AdminDao;
import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.StudentDao;
import br.edu.ifpi.data.dao.TeacherDao;
import br.edu.ifpi.entities.Student;
import br.edu.ifpi.entities.Admin;
import br.edu.ifpi.entities.enums.StudentStatus;
import br.edu.ifpi.util.AlertMessage;
import br.edu.ifpi.util.Preferences;
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

public class AdminSeeStudentsController extends AdminController {

    private ObservableList<Student> observableListStudent;

    public AdminSeeStudentsController(
            Connection connection,
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
    private TableColumn<Student, String> email;

    @FXML
    private TableColumn<Student, Integer> id;

    @FXML
    private TableColumn<Student, String> name;

    @FXML
    private TableColumn<Student, String> status;

    @FXML
    private TableView<Student> tableStudent;

    @FXML
    private Text username;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("Olá, " + admin.getName());

        loadTableStudent();
        tableStudent.setItems(observableListStudent);

        isDarkMode = Preferences.isDarkMode();

        if (isDarkMode) {
            setDarkMode();
        } else {
            setLightMode();
        }
    }

    @FXML
    void blockAccess(ActionEvent event) {
        Student student = tableStudent.getSelectionModel().getSelectedItem();

        if (student != null) {
            student.setStudentStatus(StudentStatus.INACTIVE);
            int row = super.studentDao.update(student);

            if (row > 0) {
                AlertMessage.show("Sucesso", "", "O acesso do aluno foi bloqueado com sucesso",
                        AlertType.INFORMATION);

                loadTableStudent();
                tableStudent.setItems(observableListStudent);
            } else {
                AlertMessage.show("Erro", "", "Ocorreu um erro ao bloquear o acesso do aluno",
                        AlertType.ERROR);
            }
        } else {
            AlertMessage.show("Erro", "", "Selecione um aluno para bloquear o acesso",
                    AlertType.WARNING);
        }

    }

    @FXML
    void releaseAccess(ActionEvent event) {
        Student student = tableStudent.getSelectionModel().getSelectedItem();

        if (student != null) {
            student.setStudentStatus(StudentStatus.ACTIVE);
            int row = super.studentDao.update(student);

            if (row > 0) {
                AlertMessage.show("Sucesso", "", "O acesso do aluno foi liberado com sucesso",
                        AlertType.INFORMATION);

                loadTableStudent();
                tableStudent.setItems(observableListStudent);
            } else {
                AlertMessage.show("Erro", "", "Ocorreu um erro ao liberar o acesso do aluno",
                        AlertType.ERROR);
            }
        } else {
            AlertMessage.show("Erro", "", "Selecione um aluno para liberar o acesso",
                    AlertType.WARNING);
        }
    }

    public void loadTableStudent() {
        List<Student> students = super.studentDao.selectAll();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        status.setCellValueFactory(cellData -> new SimpleStringProperty(getStatus(cellData.getValue())));

        // ordenar por id
        students.sort((t1, t2) -> t1.getId() - t2.getId());

        observableListStudent = FXCollections.observableArrayList(students);
    }

    public String getStatus(Student student) {
        if (student.getStudentStatus() == StudentStatus.ACTIVE) {
            return "Ativo";
        } else {
            return "Inativo";
        }
    }
}
