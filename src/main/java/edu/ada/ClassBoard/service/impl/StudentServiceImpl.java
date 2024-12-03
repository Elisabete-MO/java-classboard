package edu.ada.ClassBoard.service.impl;

import edu.ada.ClassBoard.controller.DTO.StudentRequestDTO;
import edu.ada.ClassBoard.controller.DTO.StudentResponseDTO;
import edu.ada.ClassBoard.model.Student;
import edu.ada.ClassBoard.model.Subject;
import edu.ada.ClassBoard.repository.PersonRepository;
import edu.ada.ClassBoard.repository.SubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentServiceImpl extends PersonServiceImpl<Student, StudentResponseDTO, StudentRequestDTO> {

    private final PersonRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public StudentServiceImpl(PersonRepository studentRepository,
                              SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<StudentResponseDTO> getAll() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponseDTO> studentResponseDTOS = new ArrayList<>();
        for (Student student : students) {
            List<Long> classes = new ArrayList<>();
            for (Subject subject : student.getSubjects()) {
                classes.add(subject.getId());
            }
            studentResponseDTOS.add(new StudentResponseDTO(student.getId(), student.getName(), student.getEmail(),
                    student.getEnrollmentDate(), classes));
        }
        return studentResponseDTOS;
    }

    @Override
    public StudentResponseDTO getById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student not found"));
        List<Long> classes = new ArrayList<>();
        for (Subject subject : student.getSubjects()) {
            classes.add(subject.getId());
        }
        return new StudentResponseDTO(student.getId(), student.getName(), student.getEmail(),
                student.getEnrollmentDate(), classes);
    }

    @Override
    public StudentResponseDTO save(StudentRequestDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setEnrollmentDate(LocalDateTime.now());
        List<Subject> subjects = new ArrayList<>();
        for (Long subjectId : dto.getClasses()) {
            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new EntityNotFoundException("Subject not found"));
            subjects.add(subject);


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
        }
    }

    @Override
    protected StudentResponseDTO convertEntityToDto(Student entity) {
        List<Long> classIds = entity.getClasses().stream()
                .map(Subject::getId)
                .collect(Collectors.toList());

        return new StudentResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getEnrollmentDate(),
                classIds
        );
    }





        }
