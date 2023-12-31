package br.edu.ifpi.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.ResultSet;

import br.edu.ifpi.data.adapters.StudentAdapter;
import br.edu.ifpi.entities.Student;
import br.edu.ifpi.entities.enums.StudentStatus;

import java.util.ArrayList;
import java.util.List;

public class StudentDao implements Dao<Student> {
    private final Connection connection;

    public StudentDao(Connection connection) {
        this.connection = connection;
    }

    public Student login(String username, String password) {
        final String sql = "SELECT * FROM Student WHERE email = ? AND password = ? AND status = '%s'"
                .formatted(StudentStatus.ACTIVE);
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return StudentAdapter.fromResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int insert(Student student) {
        final String sql = "INSERT INTO Student (name, email, password, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getPassword());
            preparedStatement.setString(4, student.getStudentStatus().toString());
            return preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            return 0;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Student student) {
        final String sql = "UPDATE Student SET name = ?, email = ?, password = ?, status = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getPassword());
            preparedStatement.setString(4, student.getStudentStatus().toString());
            preparedStatement.setInt(5, student.getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Student t) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Student select(int id) {
        final String sql = "SELECT * FROM Student WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return StudentAdapter.fromResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> selectAll() {
        final String sql = "SELECT * FROM Student";
        List<Student> students = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                students.add(StudentAdapter.fromResultSet(resultSet));
            }
            return students;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> selectAll(String condition) {
        final String sql = "SELECT * FROM Student WHERE " + condition;
        List<Student> students = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                students.add(StudentAdapter.fromResultSet(resultSet));
            }
            return students;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> selectAll(String[] conditions) {
        final String sql = "SELECT * FROM Student WHERE " + String.join(" AND ", conditions);
        List<Student> students = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                students.add(StudentAdapter.fromResultSet(resultSet));
            }
            return students;

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}