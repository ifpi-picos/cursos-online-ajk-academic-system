CREATE TABLE Course (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    status VARCHAR(20) NOT NULL,
    workload INT NOT NULL, 
    teacher_id INT NOT NULL, 
    FOREIGN KEY (teacher_id) REFERENCES Teacher(id)
);