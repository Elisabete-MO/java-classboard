package edu.ada.ClassBoard.service.impl;

import edu.ada.ClassBoard.model.Person;
import edu.ada.ClassBoard.repository.PersonRepository;
import edu.ada.ClassBoard.service.GenericPersonService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public abstract class GenericPersonServiceImpl<T, R, D> implements GenericPersonService<R,
        D> {

    protected final PersonRepository<T> repository;

    public GenericPersonServiceImpl(PersonRepository<T> repository) {
        this.repository = repository;
    }

    protected abstract T convertDtoToEntity(D dto);
    protected abstract R convertEntityToDto(T entity);
    protected abstract T updateEntityWithDto(T existingEntity, D dto);

    protected T findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with ID " + id + " not found"));
    }

    @Override
    public List<R> getAll() {
        return repository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public R getById(Long id) {
        T entity = findEntityById(id);
        return convertEntityToDto(entity);
    }

    @Override
    @Transactional
    public R save(D dto) {
        T entity = convertDtoToEntity(dto);
        T savedEntity = repository.save(entity);
        return convertEntityToDto(savedEntity);
    }

    @Override
    @Transactional
    public R update(Long id, D dto) {
        T existingEntity = findEntityById(id);
        T updatedEntity = updateEntityWithDto(existingEntity, dto);
        updatedEntity = repository.save(updatedEntity);
        return convertEntityToDto(updatedEntity);
    }

    @Override
    public void delete(Long id) {
        T entity = findEntityById(id);
        repository.delete(entity);
    }
}
