package br.edu.ifpi.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.ResultSet;

import br.edu.ifpi.data.adapters.TeacherAdapter;
import br.edu.ifpi.entities.Teacher;
import br.edu.ifpi.entities.enums.TeacherStatus;

import java.util.ArrayList;
import java.util.List;

public class TeacherDao implements Dao<Teacher> {
    private final Connection connection;

    public TeacherDao(Connection connection) {
        this.connection = connection;
    }

    public Teacher login(String username, String password) {
        final String sql = "SELECT * FROM Teacher WHERE email = ? AND password = ? AND status = '%s'"
                .formatted(TeacherStatus.ACTIVE);
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return TeacherAdapter.fromResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int insert(Teacher teacher) {
        final String sql = "INSERT INTO Teacher (name, email, password, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, teacher.getName());
            preparedStatement.setString(2, teacher.getEmail());
            preparedStatement.setString(3, teacher.getPassword());
            preparedStatement.setString(4, teacher.getTeacherStatus().toString());
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
    public int update(Teacher teacher) {
        final String sql = "UPDATE Teacher SET name = ?, email = ?, password = ?, status = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, teacher.getName());
            preparedStatement.setString(2, teacher.getEmail());
            preparedStatement.setString(3, teacher.getPassword());
            preparedStatement.setString(4, teacher.getTeacherStatus().toString());
            preparedStatement.setInt(5, teacher.getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Teacher t) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Teacher select(int id) {
        final String sql = "SELECT * FROM Teacher WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return TeacherAdapter.fromResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Teacher> selectAll() {
        final String sql = "SELECT * FROM Teacher";
        List<Teacher> teachers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                teachers.add(TeacherAdapter.fromResultSet(resultSet));
            }
            return teachers;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Teacher> selectAll(String condition) {
        final String sql = "SELECT * FROM Teacher WHERE " + condition;
        List<Teacher> teachers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                teachers.add(TeacherAdapter.fromResultSet(resultSet));
            }
            return teachers;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Teacher> selectAll(String[] conditions) {
        final String sql = "SELECT * FROM Teacher WHERE " + String.join(" AND ", conditions);
        List<Teacher> teachers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                teachers.add(TeacherAdapter.fromResultSet(resultSet));
            }
            return teachers;

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}