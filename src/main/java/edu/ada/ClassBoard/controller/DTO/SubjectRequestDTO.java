package edu.ada.ClassBoard.controller.DTO;

import java.util.List;

public record SubjectRequestDTO (String name, String code, int credits, List<Long> teachers) {
}
