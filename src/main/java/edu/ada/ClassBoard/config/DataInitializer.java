package edu.ada.ClassBoard.config;

import edu.ada.ClassBoard.model.*;
import edu.ada.ClassBoard.repository.StudentRepository;
import edu.ada.ClassBoard.repository.SubjectRepository;
import edu.ada.ClassBoard.repository.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    public DataInitializer(StudentRepository studentRepository,
                           TeacherRepository teacherRepository,
                           SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Subject math = new Subject(SubjectName.MATH, "MATH101", 4);
        Subject physics = new Subject(SubjectName.PHYSICS, "PHY101", 3);

        subjectRepository.saveAll(List.of(math, physics));

        Teacher teacher1 = new Teacher("John Connor", "john.connor@school" +
                ".com", List.of(math, physics));
        Teacher teacher2 = new Teacher("Robert Smith", "robert.smith@school" +
                ".com",
                List.of(math));
        teacherRepository.saveAll(List.of(teacher1, teacher2));

        Student student1 = new Student("Alice Copper", "alice.cooper@example" +
                ".com", List.of(math, physics));
        Student student2 = new Student("Bob Dylan", "bob.dylan@example" +
                ".com", List.of(math));
        studentRepository.saveAll(List.of(student1, student2));

        System.out.println("Data initialization complete!");
    }
}
