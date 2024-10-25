package ru.hogwarts.school.service;
import ru.hogwarts.school.model.Student;
import java.util.HashMap;
import java.util.Map;

public class StudentService {
    private Map<Long, Student> students = new HashMap<>();
    private Long idCounter = 1L;

    public Student createStudent(String name, int age) {
        Student student = new Student(idCounter++, name, age);
        students.put(student.getId(), student);
        return student;
    }

    public Student getStudent(Long id) {
        return students.get(id);
    }

    public Student updateStudent(Long id, String name, int age) {
        Student student = students.get(id);
        if (student != null) {
            student.setName(name);
            student.setAge(age);
        }
        return student;
    }

    public void deleteStudent(Long id) {
        students.remove(id);
    }

    public Map<Long, Student> getAllStudents() {
        return students;
    }

    public Map<Long, Student> filterStudentsByAge(int age) {
        Map<Long, Student> filteredStudents = new HashMap<>();
        for (Student student : students.values()) {
            if (student.getAge() == age) {
                filteredStudents.put(student.getId(), student);
            }
        }
        return filteredStudents;
    }
}
