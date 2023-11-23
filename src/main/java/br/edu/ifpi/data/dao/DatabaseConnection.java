package br.edu.ifpi.data.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/academic_system";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void closeConnection() {
        try {
            DriverManager.getConnection(URL, USER, PASSWORD).close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Boolean createTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Teacher ("
                    + "id SERIAL PRIMARY KEY,"
                    + "name VARCHAR(255) NOT NULL,"
                    + "email VARCHAR(255) NOT NULL,"
                    + "password VARCHAR(255) NOT NULL"
                    + ")");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Student ("
                    + "id SERIAL PRIMARY KEY,"
                    + "name VARCHAR(255) NOT NULL,"
                    + "email VARCHAR(255) NOT NULL,"
                    + "password VARCHAR(255) NOT NULL"
                    + ")");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Course ("
                    + "id SERIAL PRIMARY KEY,"
                    + "name VARCHAR(255) NOT NULL,"
                    + "status VARCHAR(20) NOT NULL,"
                    + "workload INT NOT NULL,"
                    + "teacher_id INT NOT NULL,"
                    + "FOREIGN KEY (teacher_id) REFERENCES Teacher(id)"
                    + ")");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Student_course ("
                    + "student_id INT NOT NULL,"
                    + "course_id INT NOT NULL,"
                    + "final_grade DECIMAL(4, 2) NOT NULL,"
                    + "status VARCHAR(255) NOT NULL, "
                    + "PRIMARY KEY (student_id, course_id),"
                    + "FOREIGN KEY (student_id) REFERENCES Student(id),"
                    + "FOREIGN KEY (course_id) REFERENCES Course(id)"
                    + ")");

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
