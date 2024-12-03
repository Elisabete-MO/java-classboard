package edu.ada.ClassBoard.controller;

import edu.ada.ClassBoard.model.Teacher;
import edu.ada.ClassBoard.service.impl.GenericPersonServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final GenericPersonServiceImpl<Teacher> teacherService;

    public TeacherController(GenericPersonServiceImpl<Teacher> teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllPersons() {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.getPersonById(id));
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher Teacher) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.savePerson(Teacher));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher Teacher) {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.updatePerson(id, Teacher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deletePerson(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
