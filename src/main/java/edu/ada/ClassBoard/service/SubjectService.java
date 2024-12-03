package edu.ada.ClassBoard.service;

import edu.ada.ClassBoard.model.Subject;
import edu.ada.ClassBoard.model.SubjectName;

import java.util.List;

public interface SubjectService {

    List<SubjectName> getAllSubjectNamesByPersonId(Long id);
//    List<Subject> getAll();
}
