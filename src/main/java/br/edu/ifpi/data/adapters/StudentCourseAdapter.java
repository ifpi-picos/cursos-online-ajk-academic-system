package br.edu.ifpi.data.adapters;

import br.edu.ifpi.data.dao.CourseDao;
import br.edu.ifpi.data.dao.StudentDao;
import br.edu.ifpi.entities.Course;
import br.edu.ifpi.entities.Student;
import br.edu.ifpi.entities.StudentCourse;
import br.edu.ifpi.entities.enums.StatusCourse;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentCourseAdapter {
    public static List<StudentCourse> fromResultSet(ResultSet resultSet, StudentDao studentDao, CourseDao courseDao) {
        try {
            List<StudentCourse> studentCourses = new ArrayList<>();
            Map<Integer, Student> studentMap = new HashMap<>();
            Map<Integer, Course> courseMap = new HashMap<>();

            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                Student student = studentMap.get(studentId);

                if (student == null) {
                    student = studentDao.select(studentId);
                    studentMap.put(studentId, student);
                }

                int courseId = resultSet.getInt("course_id");
                Course course = courseMap.get(courseId);

                if (course == null) {
                    course = courseDao.select(courseId);
                    courseMap.put(courseId, course);
                }

                StudentCourse studentCourse = new StudentCourse(
                        student,
                        course,
                        resultSet.getDouble("final_grade"),
                        StatusCourse.valueOf(resultSet.getString("status")));

                studentCourses.add(studentCourse);
            }

            return studentCourses;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}