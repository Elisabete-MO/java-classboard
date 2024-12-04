package edu.ada.ClassBoard.controller;

import edu.ada.ClassBoard.controller.DTO.TeacherRequestDTO;
import edu.ada.ClassBoard.controller.DTO.TeacherResponseDTO;
import edu.ada.ClassBoard.service.impl.TeacherServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherServiceImpl teacherService;

    public TeacherController(TeacherServiceImpl teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<TeacherResponseDTO>> getAllPersons() {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.getAll()
                .contains(null) ? null : teacherService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> getTeacherById(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TeacherResponseDTO> createTeacher(@RequestBody TeacherRequestDTO Teacher) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.save(Teacher));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> updateTeacher(@PathVariable Long id,
                                                  @RequestBody TeacherRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
