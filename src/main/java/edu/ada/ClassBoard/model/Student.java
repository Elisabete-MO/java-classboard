package edu.ada.ClassBoard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_student")
public class Student extends Person {

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime enrollmentDate;

    @ManyToMany
    @JoinTable(
            name = "student_classes",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id")
    )
    @JsonIgnore
    private List<Subject> classes;

    public Student() {
        super();
    }

    public Student(String name, String email, List<Subject> classes) {
        super(name, email);
        this.classes = classes;
    }

    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }

    public List<Subject> getClasses() {
        return classes;
    }

    public void setClasses(List<Subject> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", enrollmentDate:" + enrollmentDate +
                ", classes:" + classes +
                '}';
    }
}
