package edu.ada.ClassBoard.unit.repository;

import edu.ada.ClassBoard.model.Student;
import edu.ada.ClassBoard.model.Subject;
import edu.ada.ClassBoard.model.SubjectName;
import edu.ada.ClassBoard.model.Teacher;
import edu.ada.ClassBoard.repository.StudentRepository;
import edu.ada.ClassBoard.repository.SubjectRepository;
import edu.ada.ClassBoard.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class SubjectRepositoryTest {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Transactional
    @Test
    void findAllSubjectNamesByStudentId_ShouldReturnSubjectNames() {
        Subject subject = new Subject(SubjectName.MATH, "Math", 4);
        subjectRepository.save(subject);

        Student student = new Student("Existing Student", "existing" +
                ".studant@example.com", new ArrayList<>());
        studentRepository.save(student);

            student.getClasses().add(subject);
        subject.getStudents().add(student);

        List<String> result = subjectRepository.findAllSubjectNamesByStudentId(student.getId());

        assertEquals(student.getClasses().size(), result.size());
        assertEquals(subject.getName().toString(), result.get(0));
    }

    @Transactional
    @Test
    void findAllSubjectNamesByTeacherId_ShouldReturnSubjectNames() {
        Subject subject1 = new Subject(SubjectName.PHYSICS, "Physics", 3);
        Subject subject2 = new Subject(SubjectName.CHEMISTRY, "Chemistry", 3);
        subjectRepository.save(subject1);
        subjectRepository.save(subject2);

        Teacher teacher = new Teacher("Existing Teacher", "existing" +
                ".teacher@example.com", new ArrayList<>());
        teacherRepository.save(teacher);

        teacher.getClasses().add(subject1);
        teacher.getClasses().add(subject2);
        subject1.getTeachers().add(teacher);
        subject2.getTeachers().add(teacher);

        List<String> result =
                subjectRepository.findAllSubjectNamesByTeacherId(teacher.getId());

        assertEquals(teacher.getClasses().size(), result.size());
        assertEquals(subject1.getName().toString(), result.get(0));
        assertEquals(subject2.getName().toString(), result.get(1));
    }
}
