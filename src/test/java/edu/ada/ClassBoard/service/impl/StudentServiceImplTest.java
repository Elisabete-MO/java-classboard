package edu.ada.ClassBoard.service.impl;

import edu.ada.ClassBoard.controller.DTO.StudentRequestDTO;
import edu.ada.ClassBoard.controller.DTO.StudentResponseDTO;
import edu.ada.ClassBoard.model.Student;
import edu.ada.ClassBoard.model.Subject;
import edu.ada.ClassBoard.model.SubjectName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private SubjectServiceImpl subjectService;

    private Subject subject1;
    private Subject subject2;
    private StudentRequestDTO studentRequestDTO;
    private Student studentEntity;

    @BeforeEach
    void setUp() {
        subject1 = new Subject(1L, SubjectName.MATH, "Math", 4);
        subject2 = new Subject(2L, SubjectName.PHYSICS, "Science", 3);

        studentRequestDTO = new StudentRequestDTO(
                "New Student",
                "new.student@example.com",
                List.of(1L, 2L)
        );

        studentEntity = new Student(
                1L,
                "Existing Student",
                "existing.student@example.com",
                LocalDateTime.now(),
                List.of(subject1, subject2)
        );
    }

    @Test
    void convertRequestDtoToEntity_ShouldConvertCorrectly() {
        when(subjectService.getById(1L)).thenReturn(subject1);
        when(subjectService.getById(2L)).thenReturn(subject2);
        Student result = studentService.convertDtoToEntity(studentRequestDTO);

        assertEquals(studentRequestDTO.name(), result.getName());
        assertEquals(studentRequestDTO.email(), result.getEmail());
        assertEquals(2, result.getClasses().size());
        assertTrue(result.getClasses().contains(subject1));
        assertTrue(result.getClasses().contains(subject2));
    }

    @Test
    void convertEntityToResponseDto_ShouldConvertCorrectly() {
        StudentResponseDTO dto = studentService.convertEntityToDto(studentEntity);

        assertEquals(1L, dto.id());
        assertEquals(studentEntity.getName(), dto.name());
        assertEquals(studentEntity.getEmail(), dto.email());
        assertEquals(studentEntity.getClasses().size(), dto.classes().size());
        assertTrue(dto.classes().contains(studentEntity.getClasses().get(0).getName()));
        assertTrue(dto.classes().contains(studentEntity.getClasses().get(1).getName()));
    }

    @Test
    void updateEntityWithRequestDto_ShouldUpdateCorrectly() {
        when(subjectService.getById(2L)).thenReturn(subject2);
        StudentRequestDTO updateRequestDTO = new StudentRequestDTO(
                "New Name",
                "new.email@example.com",
                List.of(2L)
        );

        Student updatedStudent = studentService.updateEntityWithDto(studentEntity,
                updateRequestDTO);

        assertEquals(updateRequestDTO.name(), updatedStudent.getName());
        assertEquals(updateRequestDTO.email(), updatedStudent.getEmail());
        assertEquals(updatedStudent.getEnrollmentDate(),
                studentEntity.getEnrollmentDate());
        assertEquals(1, updatedStudent.getClasses().size());
        assertTrue(updatedStudent.getClasses().contains(subject2));
    }
}