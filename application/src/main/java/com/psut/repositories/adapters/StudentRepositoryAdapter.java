package com.psut.repositories.adapters;

import com.psut.models.student.Student;
import com.psut.repositories.JpaStudentRepository;
import com.psut.repositories.StudentRepository;
import com.psut.repositories.entities.StudentEntity;
import com.psut.repositories.mappers.StudentMapper;
import com.psut.exceptions.RecordNotFoundException;
import com.psut.repositories.specifications.StudentSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentRepositoryAdapter implements StudentRepository<StudentSpecifications> {
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

    public List<Student> findAll(StudentSpecifications specifications) {
        return jpaRepository.findAll(specifications)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
