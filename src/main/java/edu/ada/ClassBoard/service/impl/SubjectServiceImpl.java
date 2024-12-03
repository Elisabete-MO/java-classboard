package edu.ada.ClassBoard.service.impl;

import edu.ada.ClassBoard.model.Subject;
import edu.ada.ClassBoard.model.SubjectName;
import edu.ada.ClassBoard.repository.SubjectRepository;
import edu.ada.ClassBoard.service.SubjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<SubjectName> getAllSubjectNamesByPersonId(Long id) {
        return subjectRepository.findAll().stream().map(Subject::getName).toList();
    }
}
