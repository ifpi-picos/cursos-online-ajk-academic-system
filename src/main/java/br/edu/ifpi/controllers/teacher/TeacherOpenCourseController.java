package br.edu.ifpi.controllers.teacher;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifpi.controllers.LoginController;
import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.StudentCourseDao;
import br.edu.ifpi.entities.Course;
import br.edu.ifpi.entities.StudentCourse;
import br.edu.ifpi.entities.Teacher;
import br.edu.ifpi.entities.enums.EnrollmentStatus;
import br.edu.ifpi.util.AlertMessage;
import br.edu.ifpi.util.SceneNavigator;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class TeacherOpenCourseController extends TeacherController {

    private ObservableList<StudentCourse> observableListStudentCourse;
    private final Course course;

    public TeacherOpenCourseController(
            Connection connection,
            SceneNavigator sceneNavigator,
            Teacher teacher,
            Stage stage,
            LoginController loginController,
            CourseDao courseDao,
            StudentCourseDao studentCourseDao,
            Course course) {

        super(connection, sceneNavigator, teacher, stage, loginController, courseDao, studentCourseDao);

        this.course = course;
    }

    @FXML
    private TableColumn<StudentCourse, Double> grade;

    @FXML
    private TableColumn<StudentCourse, Integer> id;

    @FXML
    private TableColumn<StudentCourse, String> name;

    @FXML
    private Text nameCourse;

    @FXML
    private TableColumn<StudentCourse, String> status;

    @FXML
    private TableView<StudentCourse> tableCourse;

    @FXML
    private Text username;

    @FXML
    void backCourses(ActionEvent event) {

    }

    public void loadTableCourse() {
        List<StudentCourse> studentCourses = super.studentCourseDao.selectAll("course_id = " + course.getId());

        id.setCellValueFactory(
                cellData -> new SimpleIntegerProperty(cellData.getValue().getStudent().getId()).asObject());
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudent().getName()));
        grade.setCellValueFactory(new PropertyValueFactory<>("finalGrade"));

        grade.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        grade.setOnEditCommit(event -> {
            Double newGrade = event.getNewValue();

            if (newGrade < 0 || newGrade > 10) {
                AlertMessage.show("Erro", "Erro", "Nota deve ser entre 0 e 10", AlertType.ERROR);
                tableCourse.refresh();
                return;
            }

            StudentCourse studentCourse = event.getRowValue();
            if (newGrade >= 7) {
                studentCourse.setEnrollmentStatus(EnrollmentStatus.APPROVED);
            } else {
                studentCourse.setEnrollmentStatus(EnrollmentStatus.REPROVED);
            }
            studentCourse.setFinalGrade(event.getNewValue());

            int row = studentCourseDao.update(studentCourse);
            tableCourse.refresh();

            goToNextRow(event.getTablePosition().getRow() + 1);

            if (row == 0) {
                AlertMessage.show("Erro", "Erro", "Erro ao atualizar nota", AlertType.ERROR);
            }
        });

        status.setCellValueFactory(cellData -> new SimpleStringProperty(getStatus(cellData.getValue())));

        observableListStudentCourse = FXCollections.observableArrayList(studentCourses);
    }

    private void goToNextRow(int row) {
        if (row < tableCourse.getItems().size()) {
            tableCourse.getSelectionModel().select(row);
            tableCourse.getFocusModel().focus(row);
            tableCourse.scrollTo(row);
            tableCourse.edit(row, grade);
        }
    }

    public String getStatus(StudentCourse studentCourse) {
        if (studentCourse.getEnrollmentStatus() == EnrollmentStatus.PENDING) {
            return "Pendente";
        } else if (studentCourse.getEnrollmentStatus() == EnrollmentStatus.APPROVED) {
            return "Aprovado";
        } else if (studentCourse.getEnrollmentStatus() == EnrollmentStatus.CANCELED) {
            return "Cancelado";
        } else if (studentCourse.getEnrollmentStatus() == EnrollmentStatus.REPROVED) {
            return "Reprovado";
        } else {
            return "Sem status";
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("Olá, " + teacher.getName());
        nameCourse.setText(this.course.getName());

        loadTableCourse();
        tableCourse.setItems(observableListStudentCourse);
        tableCourse.setEditable(true);
    }
}
