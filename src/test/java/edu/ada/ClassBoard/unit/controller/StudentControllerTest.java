package edu.ada.ClassBoard.unit.controller;

import edu.ada.ClassBoard.DTO.StudentRequestDTO;
import edu.ada.ClassBoard.DTO.StudentResponseDTO;
import edu.ada.ClassBoard.controller.StudentController;
import edu.ada.ClassBoard.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Mock
    private StudentServiceImpl studentService;

    @InjectMocks
    private StudentController studentController;

    private StudentResponseDTO student;

    @BeforeEach
    void setUp() {
        student = new StudentResponseDTO(1L, "Joey Ramone", "joey.ramone@example.com",
                LocalDateTime.now(),
                new ArrayList<>());
    }

    @Test
    void testGetStudentById() {
        when(studentService.getById(1L)).thenReturn(student);

        ResponseEntity<StudentResponseDTO> response =
                studentController.getStudentById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(student, response.getBody());
        verify(studentService, times(1)).getById(1L);
    }

    @Test
    void testCreateStudent() {
        when(studentService.save(any(StudentRequestDTO.class))).thenReturn(student);

        ResponseEntity<StudentResponseDTO> response = studentController
                .createStudent(new StudentRequestDTO("Joey Ramone", "joey" +
                        ".ramone@teste.com", new ArrayList<>()));

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(student, response.getBody());
        verify(studentService, times(1)).save(any(StudentRequestDTO.class));
    }
}
