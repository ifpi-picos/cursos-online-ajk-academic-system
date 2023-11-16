CREATE TABLE grades (
    id SERIAL PRIMARY KEY,
    student_id INT REFERENCES student(id),
    course_id INT REFERENCES courses(id),
    grade FLOAT NOT NULL,
    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES student(id),
    CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES courses(id)
);
