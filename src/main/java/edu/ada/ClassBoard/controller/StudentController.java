package edu.ada.ClassBoard.controller;

import edu.ada.ClassBoard.controller.DTO.StudentRequestDTO;
import edu.ada.ClassBoard.controller.DTO.StudentResponseDTO;
import edu.ada.ClassBoard.model.Student;
import edu.ada.ClassBoard.service.impl.PersonServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final PersonServiceImpl<Student> studentService;

    public StudentController(PersonServiceImpl<Student> studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllPersons() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAll().contains(null) ? null : studentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getPersonById(id));
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@RequestBody StudentRequestDTO student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.savePerson(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Long id,
                                                           @RequestBody StudentRequestDTO student) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.updatePerson(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deletePerson(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
