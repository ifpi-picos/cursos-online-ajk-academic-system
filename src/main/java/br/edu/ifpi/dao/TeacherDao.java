package br.edu.ifpi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import br.edu.ifpi.adapters.TeacherAdapter;
import br.edu.ifpi.entities.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherDao implements Dao<Teacher> {
    private final Connection connection;

    public TeacherDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int insert(Teacher teacher) {
        final String sql = "INSERT INTO teacher (name, email) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, teacher.getName());
            preparedStatement.setString(2, teacher.getEmail());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Teacher teacher) {
        final String sql = "UPDATE teacher SET name = ?, email = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, teacher.getName());
            preparedStatement.setString(2, teacher.getEmail());
            preparedStatement.setInt(3, teacher.getId());
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
        final String sql = "DELETE FROM teacher WHERE id = ?";
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
    public Teacher select(int id) {
        final String sql = "SELECT * FROM teacher WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
    // Sobrescreve o método da superclasse (provavelmente de uma interface)
    // para recuperar todos os professores do banco de dados;
    public List<Teacher> selectAll() {
        // define a tabela sql para selecionar todos os campos da tabela teacher;
        final String sql = "SELECT * FROM teacher";
        List<Teacher> teachers = new ArrayList<>();
        try {
            // prepara a PrepareSratement usando a conexão e o comando sql;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // executa a consulta
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // adiciona o professor na lista de professores;
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
        final String sql = "SELECT * FROM teacher WHERE " + condition;
        List<Teacher> teachers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
        final String sql = "SELECT * FROM teacher WHERE " + String.join(" AND ", conditions);
        List<Teacher> teachers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
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