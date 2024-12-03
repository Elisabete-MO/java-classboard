package edu.ada.ClassBoard.service;

import edu.ada.ClassBoard.model.Student;

import java.util.List;


public interface PersonService<T, R, D> {

    List<R> getAll();
    R getById(Long id);
    R save(D dto);
    void delete(Long id);
    R updatePerson(Long id, D dto);
}