package br.edu.ifpi.data.adapters;

import java.sql.ResultSet;

import br.edu.ifpi.entities.Teacher;
import br.edu.ifpi.entities.enums.TeacherStatus;

public class TeacherAdapter {
    public static Teacher fromResultSet(ResultSet resultSet) {
        try {
            return new Teacher(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    TeacherStatus.valueOf(resultSet.getString("status")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
