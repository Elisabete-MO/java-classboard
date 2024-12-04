package edu.ada.ClassBoard.service.impl;

import edu.ada.ClassBoard.controller.DTO.TeacherRequestDTO;
import edu.ada.ClassBoard.controller.DTO.TeacherResponseDTO;
import edu.ada.ClassBoard.model.Subject;
import edu.ada.ClassBoard.model.SubjectName;
import edu.ada.ClassBoard.model.Teacher;
import edu.ada.ClassBoard.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl extends GenericPersonServiceImpl<Teacher,
        TeacherResponseDTO, TeacherRequestDTO> {

    private final SubjectServiceImpl subjectService;
    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository,
                              SubjectServiceImpl subjectService) {
        super(teacherRepository);
        this.teacherRepository = teacherRepository;
        this.subjectService = subjectService;
    }

    @Override
    protected Teacher convertDtoToEntity (TeacherRequestDTO dto){
        List<Subject> subjects = dto.classes().stream()
                .map(subjectService::getById)
                .toList();

        Teacher teacher = new Teacher();
        teacher.setName(dto.name());
        teacher.setEmail(dto.email());
        teacher.setClasses(subjects);
        return teacher;
    }

    @Override
    protected TeacherResponseDTO convertEntityToDto(Teacher entity) {
        List<SubjectName> classes = entity.getClasses().stream()
                .map(Subject::getName)
                .toList();

        return new TeacherResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                classes
        );
    }

    @Override
    protected Teacher updateEntityWithDto(Teacher existingEntity,
                                          TeacherRequestDTO dto) {
        existingEntity.setName(dto.name());
        existingEntity.setEmail(dto.email());
        existingEntity.setClasses(dto.classes().stream()
                .map(subjectService::getById)
                .toList());
        return existingEntity;
    }
}
