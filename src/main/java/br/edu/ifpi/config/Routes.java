package br.edu.ifpi.config;

public class Routes {
    // =========================== img ========================== //

    public static String icon = "/br/edu/ifpi/img/logo-sistema.png";
    public static String iconAlert = "/br/edu/ifpi/img/26a0.png";

    // ========================== views ========================= //

    // --- common --- //
    public static String login = "/br/edu/ifpi/views/login.fxml";
    public static String register = "/br/edu/ifpi/views/register.fxml";

    // --- admin --- //
    public static String adminHome = "/br/edu/ifpi/views/admin/home.fxml";
    public static String adminRegisterCourse = "/br/edu/ifpi/views/admin/register-course.fxml";
    public static String adminSeeCourses = "/br/edu/ifpi/views/admin/see-courses.fxml";
    public static String adminSeeTeachers = "/br/edu/ifpi/views/admin/see-teachers.fxml";
    public static String adminSeeStudents = "/br/edu/ifpi/views/admin/see-students.fxml";

    // --- student --- //
    public static String studentRegisterCourse = "/br/edu/ifpi/views/student/register-course.fxml";
    public static String studentHome = "/br/edu/ifpi/views/student/home-student.fxml";
    public static String enrolledCourse = "/br/edu/ifpi/views/student/enrolled-course.fxml";
    public static String completedCourse = "/br/edu/ifpi/views/student/completed-course.fxml";
    public static String studentProfile = "/br/edu/ifpi/views/student/student-profile.fxml";

    // --- teacher --- //
    public static String teacherHome = "/br/edu/ifpi/views/teacher/home.fxml";
    public static String teacherCourse = "/br/edu/ifpi/views/teacher/courses.fxml";
    public static String teacherCourseTaught = "/br/edu/ifpi/views/teacher/courses-taught.fxml";
    public static String teacherCourseCompletedStudent = "/br/edu/ifpi/views/teacher/courses-completeds-students.fxml";
    public static String teacherOpenCourse = "/br/edu/ifpi/views/teacher/courses-grades.fxml";
}