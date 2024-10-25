package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService = new StudentService();

    @PostMapping
    public Student createStudent(@RequestParam String name, @RequestParam int age) {
        return studentService.createStudent(name, age);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestParam String name, @RequestParam int age) {
        return studentService.updateStudent(id, name, age);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping
    public Map<Long, Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/filter/age/{age}")
    public Map<Long, Student> filterStudentsByAge(@PathVariable int age) {
        return studentService.filterStudentsByAge(age);
    }
}