package br.edu.ifpi;

import java.sql.Connection;

import br.edu.ifpi.dao.DatabaseConnection;

public class App {
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            System.out.println("Conexão estabelecida com sucesso!");
        } else {
            System.out.println("Não foi possível estabelecer conexão com o banco de dados.");
        }

    }
}
