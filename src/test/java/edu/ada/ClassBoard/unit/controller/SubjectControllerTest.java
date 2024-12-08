package edu.ada.ClassBoard.unit.controller;

import edu.ada.ClassBoard.controller.SubjectController;
import edu.ada.ClassBoard.model.Subject;
import edu.ada.ClassBoard.model.SubjectName;
import edu.ada.ClassBoard.service.impl.SubjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubjectControllerTest {

    @Mock
    private SubjectServiceImpl subjectService;

    @InjectMocks
    private SubjectController subjectController;

    private Subject subject;
    private Subject subject1;


    @BeforeEach
    void setUp() {
        subject = new Subject(1L, SubjectName.MATH, "MATH101", 4);
        subject1 = new Subject(2L, SubjectName.ENGLISH, "ENG101", 3);

    }

    @Test
    void testGetAllSubjects() {
        when(subjectService.getAll()).thenReturn(Arrays.asList(subject, subject1));

        ResponseEntity<List<Subject>> response = subjectController
                .getAllSubjects();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Arrays.asList(subject, subject1), response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals(SubjectName.MATH, response.getBody().get(0).getName());
        assertEquals(SubjectName.ENGLISH,
                response.getBody().get(1).getName());
        verify(subjectService, times(1)).getAll();
    }

    @Test
    void testGetSubjectById() {
        when(subjectService.getById(1L)).thenReturn(subject);

        ResponseEntity<Subject> response =
                subjectController.getSubjectById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(subject, response.getBody());
        verify(subjectService, times(1)).getById(1L);
    }
}
