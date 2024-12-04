package edu.ada.ClassBoard.controller;

import edu.ada.ClassBoard.model.Subject;
import edu.ada.ClassBoard.service.impl.SubjectServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectServiceImpl subjectService;

    public SubjectController(SubjectServiceImpl subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService
                .getAll().contains(null) ? null : subjectService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.getById(id));
    }
}
