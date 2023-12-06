package br.edu.ifpi.controllers.admin;

import java.util.List;
import java.util.ResourceBundle;
import java.net.URL;

import br.edu.ifpi.configs.Routes;
import br.edu.ifpi.entities.Course;
import br.edu.ifpi.entities.Teacher;
import br.edu.ifpi.entities.enums.CourseStatus;
import br.edu.ifpi.util.AlertMessage;
import br.edu.ifpi.util.Preferences;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdminRegisterController extends AdminController {

    private ObservableList<Teacher> observableListTeacher;
    private AdminController adminController;
    private Course course;

    public AdminRegisterController(AdminController adminController, Course course) {
        super(
                adminController.connection,
                adminController.sceneNavigator,
                adminController.admin,
                adminController.stage,
                adminController.courseDao,
                adminController.teacherDao,
                adminController.studentDao,
                adminController.adminDao,
                adminController.loginController);
        this.adminController = adminController;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("Olá, " + admin.getName());

        loadTeachers();
        tableTeacher.setItems(observableListTeacher);

        if (course != null) {
            title.setText("Editar curso");
            courseName.setText(course.getName());
            workload.setText(String.valueOf(course.getWorkload()));
            tableTeacher.getSelectionModel().select(getTeacherRow(course.getTeacher()));
        }

        isDarkMode = Preferences.isDarkMode();

        if (isDarkMode) {
            setDarkMode();
        } else {
            setLightMode();
        }
    }

    @FXML
    void confirm(ActionEvent event) {
        String name = courseName.getText();
        int workload = this.workload.getText().isEmpty() ? 0 : Integer.parseInt(this.workload.getText());
        Teacher teacher = tableTeacher.getSelectionModel().getSelectedItem();

        if (name.isEmpty() || workload <= 0) {
            AlertMessage.show("Erro", "", "Preencha todos os campos!", AlertType.ERROR);
            return;
        } else if (teacher == null) {
            AlertMessage.show("Erro", "", "Selecione um professor!", AlertType.ERROR);
            return;
        }

        if (this.course != null) {
            this.course.setName(name);
            this.course.setWorkload(workload);
            this.course.setTeacher(teacher);

            int row = super.courseDao.update(this.course);

            if (row > 0) {
                AlertMessage.show("Sucesso", "", "Curso atualizado com sucesso!", AlertType.INFORMATION);
                AdminSeeCoursesController adminSeeCoursesController = new AdminSeeCoursesController(adminController);
                sceneNavigator.navigateTo(Routes.adminSeeCourses, stage, adminSeeCoursesController);
            } else {
                AlertMessage.show("Erro", "", "Erro ao atualizar curso!", AlertType.ERROR);
            }
            return;
        }

        Course course = new Course(name, CourseStatus.OPEN, workload, teacher);
        int row = super.courseDao.insert(course);

        if (row > 0) {
            AlertMessage.show("Sucesso", "", "Curso cadastrado com sucesso!", AlertType.INFORMATION);

            this.courseName.setText("");
            this.workload.setText("");
            this.tableTeacher.getSelectionModel().clearSelection();
        } else {
            AlertMessage.show("Erro", "", "Erro ao cadastrar curso!", AlertType.ERROR);
        }
    }

    public void loadTeachers() {
        List<Teacher> teachers = super.teacherDao.selectAll("status = 'ACTIVE'");

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        teacherName.setCellValueFactory(new PropertyValueFactory<>("name"));

        observableListTeacher = FXCollections.observableArrayList(teachers);
    }

    public int getTeacherRow(Teacher teacher) {
        for (int i = 0; i < observableListTeacher.size(); i++) {
            if (observableListTeacher.get(i).getId() == teacher.getId()) {
                return i;
            }
        }

        return -1;
    }

}
