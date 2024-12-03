package edu.ada.ClassBoard.controller.DTO;

import edu.ada.ClassBoard.model.SubjectName;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudentResponseDTO {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime enrollmentDate;
    private List<SubjectName> classes;

    public StudentResponseDTO(Long id, String name, String email,
                              LocalDateTime enrollmentDate,
                              List<SubjectName> classes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.enrollmentDate = enrollmentDate;
        this.classes = classes != null ? classes : new ArrayList<>();
    }
}
