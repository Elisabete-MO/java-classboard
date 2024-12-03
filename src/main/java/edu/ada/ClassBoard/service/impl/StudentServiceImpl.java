package edu.ada.ClassBoard.service.impl;

import edu.ada.ClassBoard.controller.DTO.StudentRequestDTO;
import edu.ada.ClassBoard.controller.DTO.StudentResponseDTO;
import edu.ada.ClassBoard.model.Student;
import edu.ada.ClassBoard.model.Subject;
import edu.ada.ClassBoard.model.SubjectName;
import edu.ada.ClassBoard.repository.PersonRepository;
import edu.ada.ClassBoard.repository.SubjectRepository;
import edu.ada.ClassBoard.service.SubjectService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl extends GenericPersonServiceImpl<Student, StudentResponseDTO, StudentRequestDTO> {

    private final SubjectServiceImpl subjectService;
    private final PersonRepository<Student> studentRepository;

    public StudentServiceImpl(PersonRepository<Student> studentRepository,
                              SubjectServiceImpl subjectService) {
        super(studentRepository);
        this.studentRepository = studentRepository;
        this.subjectService = subjectService;
    }

//    @Override
//    public List<StudentResponseDTO> getAll() {
//        List<Student> students = studentRepository.findAll();
//        List<StudentResponseDTO> studentResponseDTOS = new ArrayList<>();
//        for (Student student : students) {
//            List<SubjectName> classes = new ArrayList<>();
//            for (Subject subject : student.getClasses()) {
//                classes.add(subject.getName());
//            }
//            studentResponseDTOS.add(new StudentResponseDTO(student.getId(), student.getName(), student.getEmail(),
//                    student.getEnrollmentDate(), classes));
//        }
//        return studentResponseDTOS;
//    }
//
//    @Override
//    public StudentResponseDTO getById(Long id) {
//        Student student = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student not found"));
//        List<SubjectName> classes = new ArrayList<>();
//        for (Subject subject : student.getClasses()) {
//            classes.add(subject.getName());
//        }
//        return new StudentResponseDTO(student.getId(), student.getName(), student.getEmail(),
//                student.getEnrollmentDate(), classes);
//    }
//
//    @Override
//    public StudentResponseDTO save(StudentRequestDTO dto) {
//        Student student = new Student();
//        student.setName(dto.getName());
//        student.setEmail(dto.getEmail());
//
//        List<Subject> subjects = new ArrayList<>();
//        for (Long subjectId : dto.getClasses()) {
//            Subject subject = subjectRepository.findById(subjectId)
//                    .orElseThrow(() -> new EntityNotFoundException("Subject not found"));
//            subjects.add(subject);
//        }
//        student.setClasses(subjects);
//
//        return convertEntityToDto(studentRepository.save(student));
//    }
//
//    @Override
//    @Transactional
//    public StudentResponseDTO update(Long id, StudentRequestDTO dto) {
//        Student existingEntity = findEntityById(id);
//        Student updatedEntity = updateEntityWithDto(existingEntity, dto);
//        updatedEntity = repository.save(updatedEntity);
//        return convertEntityToDto(updatedEntity);
//    }

    @Override
    protected Student convertDtoToEntity (StudentRequestDTO dto){
        List<Subject> subjects = dto.getClasses().stream()
                .map(id -> subjectRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Subject not found")))
                .collect(Collectors.toList());

        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setClasses(subjects);
        return student;
    }

    @Override
    protected StudentResponseDTO convertEntityToDto(Student entity) {
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
    protected Student updateEntityWithDto(Student existingEntity, StudentRequestDTO dto) {
        existingEntity.setName(dto.getName());
        existingEntity.setEmail(dto.getEmail());
        existingEntity.setClasses(dto.getClasses().stream()
                .map(id -> subjectRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Subject not found")))
                .collect(Collectors.toList()));
        return existingEntity;
    }
}
