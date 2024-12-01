package ru.hogwarts.school;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FacultyController.class)
public class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private FacultyService facultyService;

    private Faculty faculty;

    @BeforeEach
    public void setUp() {
        faculty = new Faculty("Magic", "Blue");
        faculty.setId(1L);
    }

    @Test
    public void testCreateFaculty() throws Exception {
        when(facultyService.createFaculty(any(String.class), any(String.class))).thenReturn(faculty);

        mockMvc.perform(post("/faculty?name=Magic&color=Blue")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Magic"));
    }

    @Test
    public void testGetFaculty() throws Exception {
        when(facultyService.getFaculty(1L)).thenReturn(faculty);

        mockMvc.perform(get("/faculty/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Magic"));
    }

    @Test
    public void testUpdateFaculty() throws Exception {
        when(facultyService.updateFaculty(any(Long.class), any(String.class), any(String.class))).thenReturn(faculty);

        mockMvc.perform(put("/faculty/1?name=Wizardry&color=Red")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Wizardry"));
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        mockMvc.perform(delete("/faculty/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllFaculties() throws Exception {
        when(facultyService.getAllFaculties()).thenReturn(List.of(faculty));

        mockMvc.perform(get("/faculty")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Magic"));
    }

    @Test
    public void testFilterFacultiesByNameOrColor() throws Exception {
        when(facultyService.filterFacultiesByNameOrColor("Magic", "Blue")).thenReturn(List.of(faculty));

        mockMvc.perform(get("/faculty/filter?name=Magic&color=Blue")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Magic"));
    }
}
