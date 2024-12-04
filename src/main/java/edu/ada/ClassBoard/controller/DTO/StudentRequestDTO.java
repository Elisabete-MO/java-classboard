package edu.ada.ClassBoard.controller.DTO;

import java.util.List;

public record StudentRequestDTO (String name, String email,
                                 List<Long> classes) {
}

