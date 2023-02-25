package com.psut.repositories.adapters;

import com.psut.models.student.Student;
import com.psut.repositories.JpaStudentRepository;
import com.psut.repositories.StudentRepository;
import com.psut.repositories.entities.StudentEntity;
import com.psut.repositories.mappers.StudentMapper;
import com.psut.validators.exceptions.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentRepositoryAdapter implements StudentRepository {
    private final JpaStudentRepository jpaRepository;
    private final StudentMapper mapper;

    public Student save(Student student) {
        StudentEntity studentEntity = mapper.toEntity(student);
        studentEntity = jpaRepository.save(studentEntity);
        return mapper.toDomain(studentEntity);
    }

    public Student findById(Long id) {
        StudentEntity studentEntity = jpaRepository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
        return mapper.toDomain(studentEntity);
    }
}
