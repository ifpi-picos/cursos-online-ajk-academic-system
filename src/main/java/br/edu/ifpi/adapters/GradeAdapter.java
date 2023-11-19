package br.edu.ifpi.adapters;

import java.sql.ResultSet;

import br.edu.ifpi.entities.Grade;

public class GradeAdapter {
    public static Grade fromResultSet(ResultSet resultSet) {
        try {
            return new Grade(
                    resultSet.getInt("id"),
                    resultSet.getInt("student_id"),
                    resultSet.getInt("course_id"),
                    resultSet.getFloat("grade"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
