CREATE TABLE Student_course (
    student_id INT,
    course_id INT,
    final_grade DECIMAL(4, 2),
    status VARCHAR(255), 
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES Student(id),
    FOREIGN KEY (course_id) REFERENCES Course(id)
);
