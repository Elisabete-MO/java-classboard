package edu.ada.ClassBoard.service.impl;

import edu.ada.ClassBoard.service.PersonService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public abstract class PersonServiceImpl<T, R, D> implements PersonService<T, R,
        D> {

    protected abstract T convertDtoToEntity(D dto);
    protected abstract R convertEntityToDto(T entity);

//    private final PersonRepository<T> personRepository;
//    private final SubjectRepository subjectRepository;
//
//    public PersonServiceImpl(PersonRepository<T> personRepository, SubjectRepository subjectRepository) {
//        this.personRepository = personRepository;
//        this.subjectRepository = subjectRepository;
//    }

    @Override
    public List<R> getAll() {
        return getRepository().findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());    }

    @Override
    public R getById(Long id) {
        T entity = getRepository().findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found"));
        return convertEntityToDto(entity);
    }

    @Override
    public R save(D dto) {
        T entity = convertDtoToEntity(dto);
        entity = getRepository().save(entity);
        return convertEntityToDto(entity);
    }

    @Override
    public void delete(Long id) {
        getRepository().deleteById(id);
    }

    protected abstract JpaRepository<T, Long> getRepository();
    protected abstract void updateEntity(T entity, D dto);

//    @Override
//    public T updatePerson(Long id, T person) {
//        if (personRepository.findById(id).isPresent()) personRepository.save(person);
//        return person;
//    }
//
//    private Student convertDtoToEntity(StudentRequestDTO studentRequestDTO) {
//        List<Subject> subjects = studentRequestDTO.getClasses().stream()
//                .map(classId -> subjectRepository.findById(classId)
//                        .orElseThrow(() -> new RuntimeException("Subject not found")))
//                .collect(Collectors.toList());
//
//        Student student = new Student();
//        student.setName(studentRequestDTO.getName());
//        student.setEmail(studentRequestDTO.getEmail());
//        student.setClasses(subjects);
//        return student;
//    }
//
//    private StudentResponseDTO convertEntityToResponseDTO(Student student) {
//        List<Long> subjectIds = student.getClasses().stream()
//                .map(Subject::getId)
//                .collect(Collectors.toList());
//        return new StudentResponseDTO(student.getId(), student.getName(), student.getEmail(), student.getEnrollmentDate(), subjectIds);
//    }

}
