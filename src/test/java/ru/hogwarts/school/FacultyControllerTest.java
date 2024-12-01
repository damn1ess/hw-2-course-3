package ru.hogwarts.school;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FacultyControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateFaculty() {
        ResponseEntity<Faculty> response = restTemplate.postForEntity("/faculty?name=Magic&color=Blue", null, Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Magic");
    }

    @Test
    public void testGetFaculty() {
        ResponseEntity<Faculty> response = restTemplate.getForEntity("/faculty/1", Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void testUpdateFaculty() {
        restTemplate.put("/faculty/1?name=Wizardry&color=Red", null);
        ResponseEntity<Faculty> response = restTemplate.getForEntity("/faculty/1", Faculty.class);
        assertThat(response.getBody().getName()).isEqualTo("Wizardry");
        assertThat(response.getBody().getColor()).isEqualTo("Red");
    }

    @Test
    public void testDeleteFaculty() {
        restTemplate.delete("/faculty/1");
        ResponseEntity<Faculty> response = restTemplate.getForEntity("/faculty/1", Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetAllFaculties() {
        ResponseEntity<Faculty[]> response = restTemplate.getForEntity("/faculty", Faculty[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isGreaterThan(0);
    }

    @Test
    public void testFilterFacultiesByNameOrColor() {
        ResponseEntity<Faculty[]> response = restTemplate.getForEntity("/faculty/filter?name=Magic&color=Blue", Faculty[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }
}
