package br.edu.ifpi.data.dao;

import br.edu.ifpi.data.adapters.CourseAdapter;
import br.edu.ifpi.entities.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDao implements Dao<Course> {

    private final Connection connection;

    public CourseDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int insert(Course course) {
        final String SQL = "INSERT INTO Course (name, status, workload, teacher_id) VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getStatus().toString());
            preparedStatement.setInt(3, course.getWorkload());
            preparedStatement.setInt(4, course.getTeacherId());
            int row = preparedStatement.executeUpdate();
            return row;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Course course) {
        final String SQL = "UPDATE Course SET name = ?, status = ?, workload = ?, teacher_id = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getStatus().toString());
            preparedStatement.setInt(3, course.getWorkload());
            preparedStatement.setInt(4, course.getId());
            preparedStatement.setInt(5, course.getTeacherId());
            int row = preparedStatement.executeUpdate();
            return row;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        final String SQL = "DELETE FROM Course WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            int row = preparedStatement.executeUpdate();
            return row;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Course select(int id) {
        final String SQL = "SELECT * FROM Course WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            return CourseAdapter.fromResultSet(preparedStatement.executeQuery());
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Course> selectAll() {
        final String SQL = "SELECT * FROM Course";
        List<Course> courses = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                courses.add(CourseAdapter.fromResultSet(resultSet));
            }

            return courses;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Course> selectAll(String condition) {
        final String SQL = "SELECT * FROM Course WHERE " + condition;
        List<Course> courses = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                courses.add(CourseAdapter.fromResultSet(resultSet));
            }

            return courses;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Course> selectAll(String[] conditions) {
        final String SQL = "SELECT * FROM Course WHERE " + String.join(" AND ", conditions);
        List<Course> courses = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                courses.add(CourseAdapter.fromResultSet(resultSet));
            }

            return courses;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}