package edu.ada.ClassBoard.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_subject")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private SubjectName name;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private int credits;

    @ManyToMany(mappedBy = "classes", cascade = {CascadeType.PERSIST,
            CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Teacher> teachers = new ArrayList<>();

    @ManyToMany(mappedBy = "classes", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Student> students = new ArrayList<>();

    // Constructors
    public Subject() {
    }

    public Subject(SubjectName name, String code, int credits) {
        this.name = name;
        this.code = code;
        this.credits = credits;
    }

    public Subject(Long id, SubjectName name, String code, int credits) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.credits = credits;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SubjectName getName() {
        return name;
    }

    public void setName(SubjectName name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Subjects: " +
                "id=" + id +
                ", name=" + name.getDisplayName() +
                ", code='" + code +
                ", credits=" + credits + '\n';
    }
}
