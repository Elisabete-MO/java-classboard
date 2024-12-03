package edu.ada.ClassBoard.service.impl;

import edu.ada.ClassBoard.controller.DTO.StudentResponseDTO;
import edu.ada.ClassBoard.model.Student;
import edu.ada.ClassBoard.model.Subject;
import edu.ada.ClassBoard.model.SubjectName;
import edu.ada.ClassBoard.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class GenericPersonServiceImplTest {

    @InjectMocks
    private GenericPersonServiceImpl<Student> personService;

    @Mock
    private PersonRepository<Student> personRepository;

    private Student student;
    private Student student2;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        Subject math = new Subject(1L, SubjectName.MATH, "Math", 6);
        Subject physics = new Subject(2L, SubjectName.PHYSICS, "Physics", 3);

        List<Subject> subjects = List.of(math, physics);

        student = new Student("John Connor", "john.connor@test.com",
                subjects);
        student2 = new Student("Ada Lovelace", "ada.lovelace@test.com",
                subjects);
    }


    @Test
    @DisplayName("Should return all persons(Students/Teachers) from the database")
    void getPersons() {
        when(personRepository.save(student)).thenReturn(student);
        StudentResponseDTO studentSaved = personService.save(student);
        verify(personRepository, times(1)).save(student);
    }

    @Test
    void getStudentById() {
    }

    @Test
    @DisplayName("Given student when savePerson then save student")
    public void testSavePerson() {
        when(personRepository.save(student)).thenReturn(student);
        Student studentSaved = personService.savePerson(student);
        verify(personRepository, times(1)).save(student);
        assertEquals(student.getName(),studentSaved.getName());
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void updateStudent() {
    }
}