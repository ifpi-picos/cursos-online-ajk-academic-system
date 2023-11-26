package br.edu.ifpi.data.adapters;

import java.sql.ResultSet;

import br.edu.ifpi.entities.Student;
import br.edu.ifpi.entities.enums.StudentStatus;

public class StudentAdapter {
    public static Student fromResultSet(ResultSet resultSet) {
        try {
            return new Student(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    StudentStatus.valueOf(resultSet.getString("status")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
