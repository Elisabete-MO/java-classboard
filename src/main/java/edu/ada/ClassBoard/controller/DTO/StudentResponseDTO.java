package edu.ada.ClassBoard.controller.DTO;

import edu.ada.ClassBoard.model.Student;
import edu.ada.ClassBoard.model.Subject;
import edu.ada.ClassBoard.repository.SubjectRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudentResponseDTO {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime enrollmentDate;
    private List<Long> classes;

    public StudentResponseDTO(Long id, String name, String email,
                              LocalDateTime enrollmentDate, List<Long> classes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.enrollmentDate = enrollmentDate;
        this.classes = classes != null ? classes : new ArrayList<>();
    }
}
