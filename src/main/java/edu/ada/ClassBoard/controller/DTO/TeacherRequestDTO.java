package edu.ada.ClassBoard.controller.DTO;

import java.util.List;

public record TeacherRequestDTO (String name, String email, List<Long> classes) {
}

