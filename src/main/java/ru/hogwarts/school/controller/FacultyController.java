package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Map;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService = new FacultyService();

    @PostMapping
    public Faculty createFaculty(@RequestParam String name, @RequestParam String color) {
        return facultyService.createFaculty(name, color);
    }

    @GetMapping("/{id}")
    public Faculty getFaculty(@PathVariable Long id) {
        return facultyService.getFaculty(id);
    }

    @PutMapping("/{id}")
    public Faculty updateFaculty(@PathVariable Long id, @RequestParam String name, @RequestParam String color) {
        return facultyService.updateFaculty(id, name, color);
    }

    @DeleteMapping("/{id}")
    public void deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
    }

    @GetMapping
    public Map<Long, Faculty> getAllFaculties() {
        return facultyService.getAllFaculties();
    }

    @GetMapping("/filter/color/{color}")
    public Map<Long, Faculty> filterFacultiesByColor(@PathVariable String color) {
        return facultyService.filterFacultiesByColor(color);
    }
}