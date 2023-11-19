package br.edu.ifpi.adapters;

import java.sql.ResultSet;

import br.edu.ifpi.entities.Student;

public class StudentAdapter {
    public static Student fromResultSet(ResultSet resultSet) {
        try {
            return new Student(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("email")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
