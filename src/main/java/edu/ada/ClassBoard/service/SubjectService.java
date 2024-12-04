package edu.ada.ClassBoard.service;

import edu.ada.ClassBoard.controller.DTO.SubjectRequestDTO;
import edu.ada.ClassBoard.model.Subject;
import edu.ada.ClassBoard.model.SubjectName;

import java.util.List;

public interface SubjectService {

    List<String> getAllSubjectNamesByStudentId(Long id);
    List<String> getAllSubjectNamesByTeacherId(Long id);
    List<Subject> getAll();
    Subject getById(Long id);
    Subject save(SubjectRequestDTO subject);
    Subject update(Long id, SubjectRequestDTO subject);
    void delete(Long id);
}
