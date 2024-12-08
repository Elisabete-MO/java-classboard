package edu.ada.ClassBoard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_student")
public class Student extends Person {

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime enrollmentDate;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "student_classes",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id")
    )
    @JsonIgnore
    private List<Subject> classes = new ArrayList<>();

    // Constructors
    public Student() {
        super();
    }

    public Student(String name, String email, List<Subject> classes) {
        super(name, email);
        this.classes = classes;
    }

    public Student(Long id, String name, String email, LocalDateTime enrollmentDate, List<Subject> classes) {
        super(name, email);
        this.setId(id);
        this.enrollmentDate = enrollmentDate;
        this.classes = classes;
    }

    // Getters e Setters
    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }

    public List<Subject> getClasses() {
        return classes;
    }

    public void setClasses(List<Subject> classes) {
        if (classes == null || classes.isEmpty()) {
            this.classes = new ArrayList<>();
            return;
        }

        this.classes = classes;

        for (Subject subject : classes) {
            subject.getStudents().add(this);
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                ", enrollmentDate:" + enrollmentDate +
                ", classes:" + classes +
                '}';
    }
}
