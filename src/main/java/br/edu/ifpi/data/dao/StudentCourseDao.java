package br.edu.ifpi.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edu.ifpi.data.adapters.StudentCourseAdapter;
import br.edu.ifpi.entities.Course;
import br.edu.ifpi.entities.Student;
import br.edu.ifpi.entities.StudentCourse;
import br.edu.ifpi.entities.enums.EnrollmentStatus;

public class StudentCourseDao implements Dao<StudentCourse> {

    private final Connection connection;
    private final StudentDao studentDao;
    private final CourseDao courseDao;

    public StudentCourseDao(Connection connection) {
        this.connection = connection;
        this.studentDao = new StudentDao(connection);
        this.courseDao = new CourseDao(connection);
    }

    public Double getStudentAverageGrade(Student student) {
        final String SQL = "SELECT AVG(final_grade) FROM Student_course WHERE student_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, student.getId());
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return null;
    }

    public Double getCourseApprovedQuantity(Course course, EnrollmentStatus enrollmentStatus, int quantity) {
        final String SQL = "SELECT COUNT(*) FROM Student_course WHERE course_id = ? AND status = ? AND final_grade >= 7";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, course.getId());
            preparedStatement.setString(2, enrollmentStatus.toString());
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble(1) / quantity;
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return null;
    }

    public Double getCourseAverageGrade(Course course) {
        final String SQL = "SELECT AVG(final_grade) FROM Student_course WHERE course_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, course.getId());
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return null;
    }

    @Override
    public int insert(StudentCourse studentCourse) {
        final String SQL = "INSERT INTO Student_course (student_id, course_id, final_grade, status) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, studentCourse.getStudent().getId());
            preparedStatement.setInt(2, studentCourse.getCourse().getId());
            if (studentCourse.getFinalGrade() == null) {
                preparedStatement.setNull(3, java.sql.Types.DOUBLE);
            } else {
                preparedStatement.setDouble(3, studentCourse.getFinalGrade());
            }
            preparedStatement.setString(4, studentCourse.getEnrollmentStatus().toString());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(StudentCourse studentCourse) {
        final String SQL = "UPDATE Student_course SET final_grade = ?, status = ? WHERE student_id = ? AND course_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            if (studentCourse.getFinalGrade() == null) {
                preparedStatement.setNull(1, java.sql.Types.DOUBLE);
            } else {
                preparedStatement.setDouble(1, studentCourse.getFinalGrade());
            }
            preparedStatement.setString(2, studentCourse.getEnrollmentStatus().toString());
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

    @Override
    public int delete(StudentCourse t) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public StudentCourse select(int id) {
        throw new UnsupportedOperationException("Unimplemented method 'select'");
    }

    public StudentCourse select(int student_id, int course_id) {
        final String SQL = "SELECT * FROM Student_course WHERE student_id = ? AND course_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
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

    @Override
    public List<StudentCourse> selectAll() {
        final String SQL = "SELECT * FROM Student_course";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            return StudentCourseAdapter.fromResultSet(resultSet, studentDao, courseDao);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StudentCourse> selectAll(String condition) {
        final String SQL = "SELECT * FROM Student_course WHERE " + condition;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            return StudentCourseAdapter.fromResultSet(resultSet, studentDao, courseDao);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StudentCourse> selectAll(String[] conditions) {
        final String SQL = "SELECT * FROM Student_course WHERE " + String.join(" AND ", conditions);
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            return StudentCourseAdapter.fromResultSet(resultSet, studentDao, courseDao);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<StudentCourse> findByStudent(Student student) {
        return null;
    }

    public List<StudentCourse> findByStudentAndStatus(Student student, EnrollmentStatus approved) {
        return null;
    }
}
