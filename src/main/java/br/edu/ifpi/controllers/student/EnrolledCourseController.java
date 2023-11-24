package br.edu.ifpi.controllers.student;

import java.sql.Connection;
import java.util.List;

import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.StudentCourseDao;
import br.edu.ifpi.entities.Course;
import br.edu.ifpi.entities.Student;
import br.edu.ifpi.entities.StudentCourse;
import br.edu.ifpi.util.SceneNavigator;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EnrolledCourseController extends StudentHomeController {

    private final CourseDao coursesDao;
    private final StudentCourseDao studentCourseDao;
    private ObservableList<Course> observableListCourse;

    public EnrolledCourseController(Connection connection, SceneNavigator sceneNavigator, Student student,
            Stage stage) {
        super(connection, sceneNavigator, student, stage);
        this.coursesDao = new CourseDao(connection);
        this.studentCourseDao = new StudentCourseDao(connection);
    }

    @FXML
    private TableColumn<Course, Integer> id;

    @FXML
    private TableColumn<Course, String> name;

    @FXML
    private TableColumn<Course, String> teacher;

    @FXML
    private TableColumn<Course, Integer> workload;

    @FXML
    void cancelRegistration(ActionEvent event) {

    }

    // mostrar os cursos que o aluno está matriculado e que ainda não foram
    // concluídos
    @FXML
    public void loadEnrolledStudents() {
        List<StudentCourse> studentCourses = studentCourseDao.selectAll("student_id = " + student.getId());

    }

}
