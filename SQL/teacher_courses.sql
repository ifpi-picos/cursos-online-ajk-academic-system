CREATE TABLE teacher_courses (
    teacher_id INT,
    course_id INT,
    PRIMARY KEY (teacher_id, course_id),
    FOREIGN KEY (teacher_id) REFERENCES teacher(id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
);
