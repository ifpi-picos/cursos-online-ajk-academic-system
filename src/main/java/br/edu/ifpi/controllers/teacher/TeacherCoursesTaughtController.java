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
import br.edu.ifpi.util.SceneNavigator;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TeacherCoursesTaughtController extends TeacherController {

    private ObservableList<Course> observableListCourse;

    public TeacherCoursesTaughtController(
            Connection connection,
            SceneNavigator sceneNavigator,
            Teacher teacher,
            Stage stage,
            LoginController loginController,
            CourseDao courseDao,
            StudentCourseDao studentCourseDao) {

        super(connection, sceneNavigator, teacher, stage, loginController, courseDao, studentCourseDao);
    }

    @FXML
    private TableColumn<Course, String> name;

    @FXML
    private TableColumn<Course, Integer> workload;

    @FXML
    private TableView<Course> tableCourses;

    @FXML
    private TableColumn<Course, Integer> id;

    @FXML
    private TableColumn<Course, Integer> numberStudents;

    @FXML
    private Text username;

    public void openCourse(ActionEvent event) {
        Course course = tableCourses.getSelectionModel().getSelectedItem();
        TeacherOpenCourseController teacherOpenCourseController = new TeacherOpenCourseController(
                this.connection, this.sceneNavigator, this.teacher, this.stage, this.loginController, this.courseDao,
                this.studentCourseDao, course, false);

        sceneNavigator.navigateTo(Routes.teacherOpenCourse, this.stage, teacherOpenCourseController);
    }

    public void loadTableCourse() {
        final String[] conditions = { "teacher_id = " + teacher.getId(), "status = '" + CourseStatus.CLOSED + "'" };
        List<Course> courses = super.courseDao.selectAll(conditions);
        List<StudentCourse> studentCourses = super.studentCourseDao.selectAll();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        workload.setCellValueFactory(new PropertyValueFactory<>("workload"));
        numberStudents.setCellValueFactory(
                cellData -> new SimpleIntegerProperty(studentsByCourse(cellData.getValue(), studentCourses))
                        .asObject());

        // ordenar por id
        courses.sort((c1, c2) -> c1.getId() - c2.getId());

        observableListCourse = FXCollections.observableArrayList(courses);
    }

    public int studentsByCourse(Course course, List<StudentCourse> studentCourses) {
        int count = 0;
        for (StudentCourse studentCourse : studentCourses) {
            if (studentCourse.getCourse().getId() == course.getId()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("Olá, " + teacher.getName());

        loadTableCourse();
        tableCourses.setItems(observableListCourse);
    }
}
