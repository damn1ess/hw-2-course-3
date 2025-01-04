CREATE TABLE Faculty (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    color VARCHAR(255) NOT NULL,
    UNIQUE (title, color)
);

CREATE TABLE Student (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    age INT DEFAULT 20 CHECK (age >= 16),
    faculty_id INT,
    FOREIGN KEY (faculty_id) REFERENCES Faculty(id)
);
