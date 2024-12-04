package edu.ada.ClassBoard.service.impl;

import edu.ada.ClassBoard.controller.DTO.SubjectRequestDTO;
import edu.ada.ClassBoard.model.Subject;
import edu.ada.ClassBoard.model.SubjectName;
import edu.ada.ClassBoard.model.Teacher;
import edu.ada.ClassBoard.repository.SubjectRepository;
import edu.ada.ClassBoard.repository.TeacherRepository;
import edu.ada.ClassBoard.service.SubjectService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository,
                              TeacherRepository teacherRepository) {
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<String> getAllSubjectNamesByStudentId(Long id) {
        List<String> subjectNames =
                subjectRepository.findAllSubjectNamesByStudentId(id);
        if (subjectNames.isEmpty()) {
            throw new EntityNotFoundException("No classes found for student " +
                    "with ID " + id);
        }
        return subjectNames;
    }

    @Override
    public List<String> getAllSubjectNamesByTeacherId(Long id) {
        List<String> subjectNames = subjectRepository.findAllSubjectNamesByTeacherId(id);
        if (subjectNames.isEmpty()) {
            throw new EntityNotFoundException("No classes found for teacher with ID " + id);
        }
        return subjectNames;
    }

    @Override
    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getById(Long id) {
         return subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject " + id + " not found"));
    }

    @Override
    public Subject save(SubjectRequestDTO subject) {
        return subjectRepository.save(convertDtoToEntity(subject));
    }

    @Override
    public Subject update(Long id, SubjectRequestDTO subject) {
        Subject existingSubject = getById(id);
        existingSubject.setName(SubjectName.valueOf(subject.name()));
        existingSubject.setTeachers(subject.teachers().stream()
                .map(teacherId -> teacherRepository.findById(teacherId)
                        .orElseThrow(() -> new EntityNotFoundException("Teacher not found")))
                .toList());
        return subjectRepository.save(existingSubject);
    }

    @Override
    public void delete(Long id) {
        if (getById(id) != null) subjectRepository.deleteById(id);
    }

    protected Subject convertDtoToEntity (SubjectRequestDTO dto) {
        Subject subject = new Subject();
        subject.setName(SubjectName.valueOf(dto.name()));
        List<Teacher> teachers = dto.teachers().stream()
                .map(teacherId -> teacherRepository.findById(teacherId)
                        .orElseThrow(() -> new EntityNotFoundException("Teacher not found")))
                .toList();
        subject.setTeachers(teachers);
        return subject;
    }
}
