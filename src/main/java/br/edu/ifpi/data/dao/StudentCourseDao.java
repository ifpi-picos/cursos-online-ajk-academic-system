package br.edu.ifpi.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import br.edu.ifpi.data.adapters.StudentCourseAdapter;
import br.edu.ifpi.entities.StudentCourse;
import br.edu.ifpi.entities.enums.StatusCourse;

public class StudentCourseDao {

    private final Connection connection;

    public StudentCourseDao(Connection connection) {
        this.connection = connection;
    }

    public int insert(StudentCourse studentCourse) {
        final String SQL = "INSERT INTO Student_courses (student_id, course_id, final_grade, status) VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, studentCourse.getStudentId());
            preparedStatement.setInt(2, studentCourse.getCourseId());
            preparedStatement.setDouble(3, studentCourse.getFinalGrade());
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
        final String SQL = "UPDATE Student_courses SET final_grade = ?, status = ? WHERE student_id = ? AND course_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setDouble(1, studentCourse.getFinalGrade());
            preparedStatement.setString(2, studentCourse.getStatus().toString());
            preparedStatement.setInt(3, studentCourse.getStudentId());
            preparedStatement.setInt(4, studentCourse.getCourseId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int cancel(StudentCourse studentCourse) {
        final String SQL = "UPDATE Student_courses SET status = ? WHERE student_id = ? AND course_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, StatusCourse.CANCELED.toString());
            preparedStatement.setInt(2, studentCourse.getStudentId());
            preparedStatement.setInt(3, studentCourse.getCourseId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public StudentCourse select(int student_id, int course_id) {
        final String SQL = "SELECT * FROM student_courses WHERE student_id = ? AND course_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, student_id);
            preparedStatement.setInt(2, course_id);
            return StudentCourseAdapter.fromResultSet(preparedStatement.executeQuery());
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<StudentCourse> selectAll() {
        final String SQL = "SELECT * FROM student_courses";
        List<StudentCourse> studentCourses = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                studentCourses.add(StudentCourseAdapter.fromResultSet(resultSet));
            }
            return studentCourses;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<StudentCourse> selectAll(String condition) {
        final String SQL = "SELECT * FROM student_courses WHERE " + condition;
        List<StudentCourse> studentCourses = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                studentCourses.add(StudentCourseAdapter.fromResultSet(resultSet));
            }
            return studentCourses;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<StudentCourse> selectAll(String[] conditions) {
        final String SQL = "SELECT * FROM student_courses WHERE " + String.join(" AND ", conditions);
        List<StudentCourse> studentCourses = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                studentCourses.add(StudentCourseAdapter.fromResultSet(resultSet));
            }
            return studentCourses;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
