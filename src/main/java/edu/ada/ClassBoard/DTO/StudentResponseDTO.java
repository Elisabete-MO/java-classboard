package edu.ada.ClassBoard.DTO;

import edu.ada.ClassBoard.model.SubjectName;

import java.time.LocalDateTime;
import java.util.List;

public record StudentResponseDTO (Long id, String name, String email,
                              LocalDateTime enrollmentDate,
                              List<SubjectName> classes) {
}