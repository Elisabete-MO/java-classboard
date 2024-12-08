package edu.ada.ClassBoard.service.impl;

import edu.ada.ClassBoard.DTO.StudentRequestDTO;
import edu.ada.ClassBoard.DTO.StudentResponseDTO;
import edu.ada.ClassBoard.model.Student;
import edu.ada.ClassBoard.model.Subject;
import edu.ada.ClassBoard.model.SubjectName;
import edu.ada.ClassBoard.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends GenericPersonServiceImpl<Student, StudentResponseDTO, StudentRequestDTO> {

    private final SubjectServiceImpl subjectService;
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository,
                              SubjectServiceImpl subjectService) {
        super(studentRepository);
        this.studentRepository = studentRepository;
        this.subjectService = subjectService;
    }

    @Override
    public Student convertDtoToEntity (StudentRequestDTO dto){
        List<Subject> subjects = dto.classes().stream()
                .map(subjectService::getById)
                .toList();

        Student student = new Student();
        student.setName(dto.name());
        student.setEmail(dto.email());
        student.setClasses(subjects);
        return student;
    }

    @Override
    public StudentResponseDTO convertEntityToDto(Student entity) {
        List<SubjectName> classes = entity.getClasses().stream()
                .map(Subject::getName)
                .toList();

        return new StudentResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getEnrollmentDate(),
                classes
        );
    }

    @Override
    public Student updateEntityWithDto(Student existingEntity,
                                   StudentRequestDTO dto) {
        existingEntity.setName(dto.name());
        existingEntity.setEmail(dto.email());
        existingEntity.setClasses(dto.classes().stream()
                .map(subjectService::getById)
                .toList());
            return existingEntity;
    }
}
