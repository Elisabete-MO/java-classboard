package edu.ada.ClassBoard.repository;

import edu.ada.ClassBoard.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository<T> extends JpaRepository<T, Long> {
}
