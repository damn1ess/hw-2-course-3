SELECT
    s.name AS student_name,
    s.age AS student_age,
    f.name AS faculty_name
FROM
    Student s
JOIN
    Faculty f ON s.faculty_id = f.id;

SELECT
    s.name AS student_name,
    s.age AS student_age
FROM
    Student s
WHERE
    s.avatar_url IS NOT NULL;
