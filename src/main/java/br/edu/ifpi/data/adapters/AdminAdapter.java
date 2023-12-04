package br.edu.ifpi.data.adapters;

import java.sql.ResultSet;

import br.edu.ifpi.entities.Admin;

public class AdminAdapter {
    public static Admin fromResultSet(ResultSet resultSet) {
        try {
            return new Admin(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
