package br.edu.ifpi.data.adapters;

import br.edu.ifpi.data.dao.TeacherDao;
import br.edu.ifpi.entities.Course;
import br.edu.ifpi.entities.Teacher;
import br.edu.ifpi.entities.enums.StatusCourse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class CourseAdapter {
    public static List<Course> fromResultSet(ResultSet resultSet, TeacherDao teacherDao) {
        try {
            List<Course> courses = new ArrayList<>();
            Map<Integer, Teacher> teachersMap = new HashMap<>();
   
            while (resultSet.next()) {
                int teacherId = resultSet.getInt("teacher_id");
                Teacher teacher = teachersMap.get(teacherId);

                if (teacher == null) {
                    teacher = teacherDao.select(teacherId);
                    teachersMap.put(teacherId, teacher);
                }

                Course course = new Course(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        StatusCourse.valueOf(resultSet.getString("status")),
                        resultSet.getInt("workload"),
                        teacher);

                courses.add(course);
            }

            return courses;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
