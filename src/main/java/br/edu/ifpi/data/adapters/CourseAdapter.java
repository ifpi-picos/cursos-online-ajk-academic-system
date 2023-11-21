package br.edu.ifpi.data.adapters;

import java.sql.ResultSet;

import br.edu.ifpi.entities.Course;
import br.edu.ifpi.entities.enums.StatusCourse;

public class CourseAdapter {
    public static Course fromResultSet(ResultSet resultSet) {
        try {
            return new Course(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    StatusCourse.valueOf(resultSet.getString("status")),
                    resultSet.getInt("workload"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
