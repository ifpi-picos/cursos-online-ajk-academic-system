package br.edu.ifpi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.adapters.GradeAdapter;
import br.edu.ifpi.entities.Grade;

public class GradeDao implements Dao<Grade> {

    Connection connection;

    public GradeDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int insert(Grade grade) {
        final String sql = "INSERT INTO grades (student_id, course_id, grade) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, grade.getStudentId());
            preparedStatement.setInt(2, grade.getCourseId());
            preparedStatement.setFloat(3, grade.getGrade());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Grade grade) {
        final String sql = "UPDATE grades SET student_id = ?, course_id = ?, grade = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, grade.getStudentId());
            preparedStatement.setInt(2, grade.getCourseId());
            preparedStatement.setFloat(3, grade.getGrade());
            preparedStatement.setInt(4, grade.getId());
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
        final String sql = "DELETE FROM grades WHERE id = ?";
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
    public Grade select(int id) {
        final String sql = "SELECT * FROM grades WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return GradeAdapter.fromResultSet(preparedStatement.executeQuery());
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Grade> selectAll() {
        final String sql = "SELECT * FROM grades";
        List<Grade> grades = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            while (preparedStatement.executeQuery().next()) {
                grades.add(GradeAdapter.fromResultSet(preparedStatement.executeQuery()));
            }
            return grades;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Grade> selectAll(String condition) {
        final String sql = "SELECT * FROM grades WHERE " + condition;
        List<Grade> grades = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            while (preparedStatement.executeQuery().next()) {
                grades.add(GradeAdapter.fromResultSet(preparedStatement.executeQuery()));
            }
            return grades;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Grade> selectAll(String[] conditions) {
        final String sql = "SELECT * FROM grades WHERE " + String.join(" AND ", conditions);
        List<Grade> grades = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            while (preparedStatement.executeQuery().next()) {
                grades.add(GradeAdapter.fromResultSet(preparedStatement.executeQuery()));
            }
            return grades;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}