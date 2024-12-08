package edu.ada.ClassBoard.unit.controller;

import edu.ada.ClassBoard.DTO.TeacherRequestDTO;
import edu.ada.ClassBoard.DTO.TeacherResponseDTO;
import edu.ada.ClassBoard.controller.TeacherController;
import edu.ada.ClassBoard.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeacherControllerTest {

    @Mock
    private TeacherServiceImpl teacherService;

    @InjectMocks
    private TeacherController teacherController;

    private TeacherResponseDTO teacher;

    @BeforeEach
    void setUp() {
        teacher = new TeacherResponseDTO(1L, "Joey Ramone", "joey" +
                ".ramone@example.com", new ArrayList<>());
    }

    @Test
    void testGetTeacherById() {
        when(teacherService.getById(1L)).thenReturn(teacher);

        ResponseEntity<TeacherResponseDTO> response =
                teacherController.getTeacherById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(teacher, response.getBody());
        verify(teacherService, times(1)).getById(1L);
    }

    @Test
    void testCreateTeacher() {
        when(teacherService.save(any(TeacherRequestDTO.class))).thenReturn(teacher);

        ResponseEntity<TeacherResponseDTO> response = teacherController
                .createTeacher(new TeacherRequestDTO("Joey Ramone", "joey" +
                        ".ramone@teste.com", new ArrayList<>()));

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(teacher, response.getBody());
        verify(teacherService, times(1)).save(any(TeacherRequestDTO.class));
    }
}
