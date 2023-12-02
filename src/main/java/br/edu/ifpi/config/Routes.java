package br.edu.ifpi.config;

public class Routes {
    // =========================== img ========================== //

    public static final String icon = "/br/edu/ifpi/img/logo-sistema.png";
    public static final String iconAlert = "/br/edu/ifpi/img/26a0.png";

    // ========================== views ========================= //

    // --- common --- //
    public static final String login = "/br/edu/ifpi/views/login.fxml";
    public static final String register = "/br/edu/ifpi/views/register.fxml";

    // --- admin --- //
    public static final String adminHome = "/br/edu/ifpi/views/admin/home.fxml";
    public static final String adminRegisterCourse = "/br/edu/ifpi/views/admin/register-course.fxml";
    public static final String adminSeeCourses = "/br/edu/ifpi/views/admin/see-courses.fxml";
    public static final String adminSeeTeachers = "/br/edu/ifpi/views/admin/see-teachers.fxml";
    public static final String adminSeeStudents = "/br/edu/ifpi/views/admin/see-students.fxml";

    // --- student --- //
    public static final String studentRegisterCourse = "/br/edu/ifpi/views/student/register-course.fxml";
    public static final String studentHome = "/br/edu/ifpi/views/student/home-student.fxml";
    public static final String enrolledCourse = "/br/edu/ifpi/views/student/enrolled-course.fxml";
    public static final String completedCourse = "/br/edu/ifpi/views/student/completed-course.fxml";
    public static final String studentProfile = "/br/edu/ifpi/views/student/student-profile.fxml";

    // --- teacher --- //
    public static final String teacherHome = "/br/edu/ifpi/views/teacher/home.fxml";
    public static final String teacherCourse = "/br/edu/ifpi/views/teacher/courses.fxml";
    public static final String teacherCourseTaught = "/br/edu/ifpi/views/teacher/courses-taught.fxml";
    public static final String teacherCourseCompletedStudent = "/br/edu/ifpi/views/teacher/courses-completeds-students.fxml";
    public static final String teacherOpenCourse = "/br/edu/ifpi/views/teacher/courses-grades.fxml";
}