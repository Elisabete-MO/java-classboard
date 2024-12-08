package edu.ada.ClassBoard.DTO;

import java.util.List;

public record SubjectResponseDTO(Long id, String name, String code, int credits,
                                 List<Long> teachers) {
}
