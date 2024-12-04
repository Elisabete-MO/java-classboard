package edu.ada.ClassBoard.controller.DTO;

import java.util.List;

public record SubjectResponseDTO(Long id, String name, String code, int credits,
                                 List<Long> teachers) {
}
