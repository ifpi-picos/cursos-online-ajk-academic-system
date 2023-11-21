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
            // Criação da tabela courses
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS courses ("
                    + "id SERIAL PRIMARY KEY,"
                    + "name VARCHAR(255) NOT NULL,"
                    + "status VARCHAR(20) NOT NULL,"
                    + "workload INT NOT NULL"
                    + ")");

            // Criação da tabela student
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS student ("
                    + "id SERIAL PRIMARY KEY,"
                    + "name VARCHAR(255) NOT NULL,"
                    + "email VARCHAR(255) NOT NULL,"
                    + "password VARCHAR(255) NOT NULL"
                    + ")");

            // Criação da tabela teacher
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS teacher ("
                    + "id SERIAL PRIMARY KEY,"
                    + "name VARCHAR(255) NOT NULL,"
                    + "email VARCHAR(255) NOT NULL,"
                    + "password VARCHAR(255) NOT NULL"
                    + ")");

            // Criação da tabela grades
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS grades ("
                    + "id SERIAL PRIMARY KEY,"
                    + "student_id INT REFERENCES student(id),"
                    + "course_id INT REFERENCES courses(id),"
                    + "grade FLOAT NOT NULL"
                    + ")");

            // Criação da tabela student_courses
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS student_courses ("
                    + "student_id INT REFERENCES student(id),"
                    + "course_id INT REFERENCES courses(id),"
                    + "PRIMARY KEY (student_id, course_id)"
                    + ")");

            // Criação da tabela teacher_courses
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS teacher_courses ("
                    + "teacher_id INT REFERENCES teacher(id),"
                    + "course_id INT REFERENCES courses(id),"
                    + "PRIMARY KEY (teacher_id, course_id)"
                    + ")");

            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
