package edu.ada.ClassBoard.config;

import edu.ada.ClassBoard.model.Student;
import edu.ada.ClassBoard.model.Subject;
import edu.ada.ClassBoard.model.SubjectName;
import edu.ada.ClassBoard.model.Teacher;
import edu.ada.ClassBoard.repository.StudentRepository;
import edu.ada.ClassBoard.repository.SubjectRepository;
import edu.ada.ClassBoard.repository.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(
            TeacherRepository teacherRepository,
            StudentRepository studentRepository,
            SubjectRepository subjectRepository) {

        return args -> {
            Subject math = new Subject(SubjectName.MATH, "MATH101", 4);
            Subject science = new Subject(SubjectName.SCIENCE, "SCI101", 3);
            Subject english = new Subject(SubjectName.ENGLISH, "ENG101", 2);

            subjectRepository.saveAll(Arrays.asList(math, science, english));

            Teacher teacher1 = new Teacher("John Smith", "john.smith@example" +
                    ".com", new ArrayList<>());
            Teacher teacher2 = new Teacher("Jane Doe", "jane.doe@example.com"
                    , new ArrayList<>());

            teacherRepository.saveAll(Arrays.asList(teacher1, teacher2));

            Student student1 = new Student("Alice Johnson", "alice" +
                    ".johnson@example.com", new ArrayList<>());
            Student student2 = new Student("Bob Brown", "bob.brown@example" +
                    ".com", new ArrayList<>());

            studentRepository.saveAll(Arrays.asList(student1, student2));

            List<Subject> classes1 = new ArrayList<>();
            classes1.add(math);
            classes1.add(science);
            teacher1.setClasses(classes1);
            student1.setClasses(classes1);
            teacherRepository.save(teacher1);
            studentRepository.save(student1);

            List<Subject> classes2 = new ArrayList<>();
            classes2.add(english);
            teacher2.setClasses(classes2);
            student2.setClasses(classes2);
            teacherRepository.save(teacher2);
            studentRepository.save(student2);

            System.out.println("Database initialized");
        };
    }
}
