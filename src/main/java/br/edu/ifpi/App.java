package br.edu.ifpi;

import br.edu.ifpi.dao.DatabaseConnection;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getConnection();
    }
}
