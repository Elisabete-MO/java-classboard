package edu.ada.ClassBoard.integration;

import edu.ada.ClassBoard.model.Student;
import edu.ada.ClassBoard.model.Subject;
import edu.ada.ClassBoard.model.SubjectName;
import edu.ada.ClassBoard.repository.StudentRepository;
import edu.ada.ClassBoard.repository.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    private Student student;

    @BeforeEach
    void setup() {
        studentRepository.deleteAll();
        subjectRepository.deleteAll();

        Subject subject =
            new Subject(SubjectName.MATH, "Math", 4);
        subjectRepository.save(subject);

        Student student = new Student("Joey Ramone", "joey.ramone@example.com", new ArrayList<>());
        student.setClasses((List<Subject>) subject);
        studentRepository.save(student);
    }

    @Test
    void testCreateStudentIntegration() throws Exception {
        String requestBody = """
            {
                "name": "Bob Ramone",
                "email": "bob.ramone@example.com",
                "classes": []
            }
            """;

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Joey Ramone"))
                .andExpect(jsonPath("$.email").value("joey.ramone@example.com"));

        assertEquals(1, studentRepository.count());
    }

    @Test
    void testGetStudentByIdIntegration() throws Exception {

        mockMvc.perform(get("/students/" + student.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Joey Ramone"))
                .andExpect(jsonPath("$.email").value("joey.ramone@example.com"));
    }
}

