package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

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
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/filter/age")
    public List<Student> filterStudentsByAge(@RequestParam int min, @RequestParam int max) {
        return studentService.filterStudentsByAge(min, max);
    }

    @GetMapping("/filter/name")
    public List<Student> filterStudentsByName(@RequestParam String name) {
        return studentService.filterStudentsByName(name);
    }
}