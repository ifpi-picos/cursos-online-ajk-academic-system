package br.edu.ifpi.controllers.teacher;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifpi.config.Routes;
import br.edu.ifpi.controllers.LoginController;
import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.StudentCourseDao;
import br.edu.ifpi.entities.Course;
import br.edu.ifpi.entities.StudentCourse;
import br.edu.ifpi.entities.Teacher;
import br.edu.ifpi.entities.enums.CourseStatus;
import br.edu.ifpi.entities.enums.EnrollmentStatus;
import br.edu.ifpi.util.AlertMessage;
import br.edu.ifpi.util.Preferences;
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
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;


public class TeacherOpenCourseController extends TeacherController {

    private ObservableList<StudentCourse> observableListStudentCourse;
    private final Course course;
    private final Boolean edit;

    public TeacherOpenCourseController(
            Connection connection,
            SceneNavigator sceneNavigator,
            Teacher teacher,
            Stage stage,
            LoginController loginController,
            CourseDao courseDao,
            StudentCourseDao studentCourseDao,
            Course course,
            Boolean edit) {

        super(connection, sceneNavigator, teacher, stage, loginController, courseDao, studentCourseDao);

        this.course = course;
        this.edit = edit;
    }

    @FXML
    private Text classAverage;

    @FXML
    private Text classPerformance;

    @FXML
    private Text numberStudents;

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
    private Button finishCourse;

    @FXML
    void backCourses(ActionEvent event) {
        if (edit) {
            TeacherCourseController teacherCourseController = new TeacherCourseController(
                    this.connection, this.sceneNavigator, this.teacher, this.stage, this.loginController,
                    this.courseDao, this.studentCourseDao);
            sceneNavigator.navigateTo(Routes.teacherCourse, this.stage, teacherCourseController);
        } else {
            TeacherCoursesTaughtController teacherCoursesTaughtController = new TeacherCoursesTaughtController(
                    this.connection, this.sceneNavigator, this.teacher, this.stage, this.loginController,
                    this.courseDao, this.studentCourseDao);
            sceneNavigator.navigateTo(Routes.teacherCourseTaught, this.stage, teacherCoursesTaughtController);
        }
    }

    @FXML
    void finishCourse(ActionEvent event) {
        course.setStatus(CourseStatus.CLOSED);

        int row = courseDao.update(course);

        if (row > 0) {
            AlertMessage.show("Sucesso", "", "Curso finalizado com sucesso", AlertType.INFORMATION);
            TeacherCourseController teacherCourseController = new TeacherCourseController(
                    this.connection, this.sceneNavigator, this.teacher, this.stage, this.loginController,
                    this.courseDao, this.studentCourseDao);
            sceneNavigator.navigateTo(Routes.teacherCourse, this.stage, teacherCourseController);
        } else {
            AlertMessage.show("Erro", "", "Erro ao finalizar curso", AlertType.ERROR);
        }
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
                AlertMessage.show("Erro", "", "Nota deve ser entre 0 e 10", AlertType.ERROR);
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
            loadClassStatistics();

            if (row == 0) {
                AlertMessage.show("Erro", "", "Erro ao atualizar nota", AlertType.ERROR);
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

    private void loadClassStatistics() {
        Double courseApprovedQuantity = studentCourseDao.getCourseApprovedQuantity(
                course,
                EnrollmentStatus.APPROVED,
                observableListStudentCourse.size())
                * 100;

        classAverage.setText(studentCourseDao.getCourseAverageGrade(course).toString());
        classPerformance.setText(courseApprovedQuantity.intValue() + "%");
        numberStudents.setText(String.valueOf(observableListStudentCourse.size()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTableCourse();
        tableCourse.setItems(observableListStudentCourse);

        loadClassStatistics();

        username.setText("Olá, " + teacher.getName());

        if (edit) {
            finishCourse.setVisible(true);
            tableCourse.setEditable(true);
            nameCourse.setText(this.course.getName());
        } else {
            finishCourse.setVisible(false);
            tableCourse.setEditable(false);
            nameCourse.setText(this.course.getName() + " - FINALIZADO");
        }

        isDarkMode = Preferences.isDarkMode();

        if (isDarkMode) {
            setDarkMode();
        } else {
            setLightMode();
        }
    }
}
