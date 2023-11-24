package br.edu.ifpi.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edu.ifpi.data.adapters.StudentCourseAdapter;
import br.edu.ifpi.entities.StudentCourse;

public class StudentCourseDao {

    private final Connection connection;
    private final StudentDao studentDao;
    private final CourseDao courseDao;

    public StudentCourseDao(Connection connection) {
        this.connection = connection;
        this.studentDao = new StudentDao(connection);
        this.courseDao = new CourseDao(connection);
    }

    public int insert(StudentCourse studentCourse) {
        final String SQL = "INSERT INTO Student_course (student_id, course_id, final_grade, status) VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, studentCourse.getStudent().getId());
            preparedStatement.setInt(2, studentCourse.getCourse().getId());
            if (studentCourse.getFinalGrade() == null) {
                preparedStatement.setNull(3, java.sql.Types.DOUBLE);
            } else {
                preparedStatement.setDouble(3, studentCourse.getFinalGrade());
            }
            preparedStatement.setString(4, studentCourse.getStatus().toString());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(StudentCourse studentCourse) {
        final String SQL = "UPDATE Student_course SET final_grade = ?, status = ? WHERE student_id = ? AND course_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setDouble(1, studentCourse.getFinalGrade());
            preparedStatement.setString(2, studentCourse.getStatus().toString());
            preparedStatement.setInt(3, studentCourse.getStudent().getId());
            preparedStatement.setInt(4, studentCourse.getCourse().getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public StudentCourse select(int student_id, int course_id) {
        final String SQL = "SELECT * FROM Student_course WHERE student_id = ? AND course_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, student_id);
            preparedStatement.setInt(2, course_id);
            return StudentCourseAdapter.fromResultSet(preparedStatement.executeQuery(), studentDao, courseDao).get(0);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<StudentCourse> selectAll() {
        final String SQL = "SELECT * FROM Student_course";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            final ResultSet resultSet = preparedStatement.executeQuery();
            return StudentCourseAdapter.fromResultSet(resultSet, studentDao, courseDao);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<StudentCourse> selectAll(String condition) {
        final String SQL = "SELECT * FROM Student_course WHERE " + condition;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            final ResultSet resultSet = preparedStatement.executeQuery();
            return StudentCourseAdapter.fromResultSet(resultSet, studentDao, courseDao);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<StudentCourse> selectAll(String[] conditions) {
        final String SQL = "SELECT * FROM Student_course WHERE " + String.join(" AND ", conditions);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            final ResultSet resultSet = preparedStatement.executeQuery();
            return StudentCourseAdapter.fromResultSet(resultSet, studentDao, courseDao);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
