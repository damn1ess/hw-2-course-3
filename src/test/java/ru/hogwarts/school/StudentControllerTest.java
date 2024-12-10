package ru.hogwarts.school;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    private Student student;

    @BeforeEach
    public void setUp() {
        student = new Student("John", 20);
        student.setId(1L);
    }

    @Test
    public void testCreateStudent() throws Exception {
        when(studentService.createStudent(any(String.class), any(Integer.class))).thenReturn(student);

        mockMvc.perform(post("/student?name=John&age=20")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    public void testGetStudent() throws Exception {
        when(studentService.getStudent(1L)).thenReturn(student);

        mockMvc.perform(get("/student/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    public void testUpdateStudent() throws Exception {
        when(studentService.updateStudent(any(Long.class), any(String.class), any(Integer.class))).thenReturn(student);

        mockMvc.perform(put("/student/1?name=Jane&age=21")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane"));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        mockMvc.perform(delete("/student/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllStudents() throws Exception {
        when(studentService.getAllStudents()).thenReturn(List.of(student));

        mockMvc.perform(get("/student")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John"));
    }

    @Test
    public void testFilterStudentsByAge() throws Exception {
        when(studentService.filterStudentsByAge(10, 20)).thenReturn(List.of(student));

        mockMvc.perform(get("/student/filter/age?min=10&max=20")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John"));
    }

    @Test
    public void testFilterStudentsByName() throws Exception {
        when(studentService.filterStudentsByName("John")).thenReturn(List.of(student));

        mockMvc.perform(get("/student/filter/name?name=John")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John"));
    }
}