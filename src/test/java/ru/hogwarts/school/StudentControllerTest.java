package ru.hogwarts.school;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Student;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
public
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        // Здесь можно добавить начальные данные в базу данных, если это необходимо
    }

    @Test
    public void testCreateStudent() {
        ResponseEntity<Student> response = restTemplate.postForEntity("/student?name=John&age=20", null, Student.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("John");
    }

    @Test
    public void testGetStudent() {
        ResponseEntity<Student> response = restTemplate.getForEntity("/student/1", Student.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void testUpdateStudent() {
        restTemplate.put("/student/1?name=Jane&age=21", null);
        ResponseEntity<Student> response = restTemplate.getForEntity("/student/1", Student.class);
        assertThat(Objects.requireNonNull(response.getBody()).getName()).isEqualTo("Jane");
        assertThat(response.getBody().getAge()).isEqualTo(21);
    }

    @Test
    public void testDeleteStudent() {
        restTemplate.delete("/student/1");
        ResponseEntity<Student> response = restTemplate.getForEntity("/student/1", Student.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetAllStudents() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity("/student", Student[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isGreaterThan(0);
    }

    @Test
    public void testFilterStudentsByAge() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity("/student/filter/age?min=10&max=20", Student[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void testFilterStudentsByName() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity("/student/filter/name?name=John", Student[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }
}