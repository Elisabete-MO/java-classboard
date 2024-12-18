package edu.ada.ClassBoard.controller;

import edu.ada.ClassBoard.DTO.StudentRequestDTO;
import edu.ada.ClassBoard.DTO.StudentResponseDTO;
import edu.ada.ClassBoard.service.impl.StudentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAll().contains(null) ? null : studentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getById(id));
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@RequestBody StudentRequestDTO student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Long id,
                                                           @RequestBody StudentRequestDTO student) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.update(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
