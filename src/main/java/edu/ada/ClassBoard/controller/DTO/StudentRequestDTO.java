package edu.ada.ClassBoard.controller.DTO;

import edu.ada.ClassBoard.model.Student;
import edu.ada.ClassBoard.model.Subject;
import edu.ada.ClassBoard.repository.SubjectRepository;

import java.util.ArrayList;
import java.util.List;

public class StudentRequestDTO {
    private String name;
    private String email;
    private List<Long> classes;

    public StudentRequestDTO(String name, String email, List<Long> classes) {
        this.name = name;
        this.email = email;
        this.classes = classes != null ? classes : new ArrayList<>();
    }

    public Student convertDtoToEntity(StudentRequestDTO studentRequestDTO,
                                      SubjectRepository subjectRepository) {
        List<Subject> subjects = new ArrayList<>();
        for (Long classId : studentRequestDTO.getClasses()) {
            Subject subject = subjectRepository.findById(classId)
                    .orElseThrow(() -> new RuntimeException("Subject not found"));
            subjects.add(subject);
        }

        Student student = new Student();
        student.setName(studentRequestDTO.getName());
        student.setEmail(studentRequestDTO.getEmail());
        student.setClasses(subjects);

        return student;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Long> getClasses() {
        return classes;
    }
}
