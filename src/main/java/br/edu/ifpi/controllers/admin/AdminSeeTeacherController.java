package br.edu.ifpi.controllers.admin;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifpi.data.dao.TeacherDao;
import br.edu.ifpi.entities.Admin;
import br.edu.ifpi.entities.Teacher;
import br.edu.ifpi.entities.enums.TeacherStatus;
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

public class AdminSeeTeacherController extends AdminHomeController {

    private ObservableList<Teacher> observableListTeacher;
    private final TeacherDao teacherDao;

    public AdminSeeTeacherController(Connection connection, SceneNavigator sceneNavigator, Admin admin, Stage stage) {
        super(connection, sceneNavigator, admin, stage);
        teacherDao = new TeacherDao(connection);
    }

    @FXML
    private TableColumn<Teacher, String> email;

    @FXML
    private TableColumn<Teacher, Integer> id;

    @FXML
    private TableColumn<Teacher, String> name;

    @FXML
    private TableColumn<Teacher, String> status;

    @FXML
    private TableView<Teacher> tableTeacher;

    @FXML
    private Text username;

    @FXML
    void blockAccess(ActionEvent event) {
        Teacher teacher = tableTeacher.getSelectionModel().getSelectedItem();

        if (teacher == null) {
            AlertMessage.show("Erro", "Selecione um professor", "Selecione um professor para bloquear o acesso",
                    AlertType.WARNING);
            return;
        } else {
            teacher.setTeacherStatus(TeacherStatus.INACTIVE);
            int row = teacherDao.update(teacher);

            if (row > 0) {
                AlertMessage.show("Sucesso", "Professor bloqueado",
                        "O professor " + teacher.getName() + " foi bloqueado com sucesso", AlertType.INFORMATION);

                loadTableTeacher();
                tableTeacher.setItems(observableListTeacher);
            } else {
                AlertMessage.show("Erro", "Erro ao bloquear professor",
                        "Ocorreu um erro ao bloquear o professor " + teacher.getName(), AlertType.ERROR);
            }
        }
    }

    @FXML
    void releaseAccess(ActionEvent event) {
        Teacher teacher = tableTeacher.getSelectionModel().getSelectedItem();

        if (teacher == null) {
            AlertMessage.show("Erro", "Selecione um professor", "Selecione um professor para liberar o acesso",
                    AlertType.WARNING);
            return;
        } else {
            teacher.setTeacherStatus(TeacherStatus.ACTIVE);
            int row = teacherDao.update(teacher);

            if (row > 0) {
                AlertMessage.show("Sucesso", "Professor liberado",
                        "O professor " + teacher.getName() + " foi liberado com sucesso", AlertType.INFORMATION);

                loadTableTeacher();
                tableTeacher.setItems(observableListTeacher);
            } else {
                AlertMessage.show("Erro", "Erro ao liberar professor",
                        "Ocorreu um erro ao liberar o professor " + teacher.getName(), AlertType.ERROR);
            }
        }
    }

    public void loadTableTeacher() {
        List<Teacher> teachers = teacherDao.selectAll();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        status.setCellValueFactory(cellData -> new SimpleStringProperty(getStatus(cellData.getValue())));

        // ordenar por id
        teachers.sort((t1, t2) -> t1.getId() - t2.getId());

        observableListTeacher = FXCollections.observableArrayList(teachers);
    }

    public String getStatus(Teacher teacher) {
        if (teacher.getTeacherStatus() == TeacherStatus.ACTIVE) {
            return "Ativo";
        } else {
            return "Inativo";
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("Olá, " + admin.getName());

        loadTableTeacher();
        tableTeacher.setItems(observableListTeacher);
    }

}
