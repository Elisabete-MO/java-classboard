package edu.ada.ClassBoard.controller.DTO;

import edu.ada.ClassBoard.model.SubjectName;

import java.util.List;

public record TeacherResponseDTO(Long id, String name, String email,
                                 List<SubjectName> classes) {
}

