package br.edu.ifpi.data.adapters;

import br.edu.ifpi.entities.StudentCourse;
import br.edu.ifpi.entities.enums.StatusCourse;
import java.sql.ResultSet;

public class StudentCourseAdapter {
    public static StudentCourse fromResultSet(ResultSet resultSet) {
        try {
            return new StudentCourse(
                    resultSet.getInt("student_id"),
                    resultSet.getInt("course_id"),
                    resultSet.getDouble("final_grade"),
                    StatusCourse.valueOf(resultSet.getString("status")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
