package edu.ada.ClassBoard.service;

import java.util.List;

public interface GenericPersonService<R, D> {

    List<R> getAll();
    R getById(Long id);
    R save(D dto);
    R update(Long id, D dto);
    void delete(Long id);
}