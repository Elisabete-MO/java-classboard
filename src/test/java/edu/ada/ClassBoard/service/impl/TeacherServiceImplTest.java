package edu.ada.ClassBoard.service.impl;

import edu.ada.ClassBoard.controller.DTO.TeacherRequestDTO;
import edu.ada.ClassBoard.controller.DTO.TeacherResponseDTO;
import edu.ada.ClassBoard.model.Teacher;
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
class TeacherServiceImplTest {

    @InjectMocks
    private TeacherServiceImpl teacherService;

    @Mock
    private SubjectServiceImpl subjectService;

    private Subject subject1;
    private Subject subject2;
    private TeacherRequestDTO teacherRequestDTO;
    private Teacher teacherEntity;

    @BeforeEach
    void setUp() {
        subject1 = new Subject(1L, SubjectName.MATH, "Math", 4);
        subject2 = new Subject(2L, SubjectName.PHYSICS, "Science", 3);

        teacherRequestDTO = new TeacherRequestDTO(
                "New Teacher",
                "new.teacher@example.com",
                List.of(1L, 2L)
        );

        teacherEntity = new Teacher(
                1L,
                "Existing Teacher",
                "existing.teacher@example.com",
                List.of(subject1, subject2)
        );
    }

    @Test
    void convertRequestDtoToEntity_ShouldConvertCorrectly() {
        when(subjectService.getById(1L)).thenReturn(subject1);
        when(subjectService.getById(2L)).thenReturn(subject2);
        Teacher result = teacherService.convertDtoToEntity(teacherRequestDTO);

        assertEquals(teacherRequestDTO.name(), result.getName());
        assertEquals(teacherRequestDTO.email(), result.getEmail());
        assertEquals(2, result.getClasses().size());
        assertTrue(result.getClasses().contains(subject1));
        assertTrue(result.getClasses().contains(subject2));
    }

    @Test
    void convertEntityToResponseDto_ShouldConvertCorrectly() {
        TeacherResponseDTO dto = teacherService.convertEntityToDto(teacherEntity);

        assertEquals(1L, dto.id());
        assertEquals(teacherEntity.getName(), dto.name());
        assertEquals(teacherEntity.getEmail(), dto.email());
        assertEquals(teacherEntity.getClasses().size(), dto.classes().size());
        assertTrue(dto.classes().contains(teacherEntity.getClasses().get(0).getName()));
        assertTrue(dto.classes().contains(teacherEntity.getClasses().get(1).getName()));
    }

    @Test
    void updateEntityWithDtoRequest_ShouldUpdateCorrectly() {
        when(subjectService.getById(2L)).thenReturn(subject2);
        TeacherRequestDTO updateRequestDTO = new TeacherRequestDTO(
                "New Name",
                "new.email@example.com",
                List.of(2L)
        );

        Teacher updatedTeacher = teacherService.updateEntityWithDto(teacherEntity,
                updateRequestDTO);

        assertEquals(updateRequestDTO.name(), updatedTeacher.getName());
        assertEquals(updateRequestDTO.email(), updatedTeacher.getEmail());
        assertEquals(1, updatedTeacher.getClasses().size());
        assertTrue(updatedTeacher.getClasses().contains(subject2));
    }
}