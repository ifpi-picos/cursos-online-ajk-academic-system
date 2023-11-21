package br.edu.ifpi.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import br.edu.ifpi.data.adapters.StudentAdapter;
import br.edu.ifpi.entities.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDao implements Dao<Student> {
    private final Connection connection;

    public StudentDao(Connection connection) {
        this.connection = connection;
    }

    public Student login(String username, String password) {
        final String sql = "SELECT * FROM student WHERE (email = ? OR name = ?) AND password = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
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
        final String sql = "INSERT INTO student (name, email, password) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getPassword());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Student student) {
        final String sql = "UPDATE student SET name = ?, email = ?, password = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getPassword());
            preparedStatement.setInt(4, student.getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        final String sql = "DELETE FROM student WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Student select(int id) {
        final String sql = "SELECT * FROM student WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
        final String sql = "SELECT * FROM student";
        List<Student> students = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
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
        final String sql = "SELECT * FROM student WHERE " + condition;
        List<Student> students = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
        final String sql = "SELECT * FROM student WHERE " + String.join(" AND ", conditions);
        List<Student> students = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
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