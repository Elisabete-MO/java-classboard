package edu.ada.ClassBoard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_teacher")
public class Teacher extends Person {

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "teacher_classes",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id")
    )
    @JsonIgnore
    private List<Subject> classes;

    // Constructors
    public Teacher() {
        super();
    }

    public Teacher(String name, String email, List<Subject> classes) {
        super(name, email);
        this.classes = classes;
    }

    public Teacher(Long id, String name, String email, List<Subject> classes) {
        super(name, email);
        this.setId(id);
        this.classes = classes;
    }

    // Getters e Setters
    public List<Subject> getClasses() {
        return classes;
    }

    public void setClasses(List<Subject> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", classes:" + classes +
                '}';
    }
}
