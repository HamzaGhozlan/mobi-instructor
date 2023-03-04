package com.psut.repository.impl;

import com.psut.exception.RecordNotFoundException;
import com.psut.model.student.Student;
import com.psut.repository.JpaStudentRepository;
import com.psut.repository.entities.StudentEntity;
import com.psut.repository.mappers.StudentMapper;
import com.psut.repository.specifications.StudentSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentRepository {
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

    public List<Student> findAll(Student example) {
        Example<StudentEntity> example1 = Example.of(mapper.toEntity(example));
        return getDomainListFromEntity(jpaRepository.findAll(example1));
    }

    public List<Student> findAll(StudentSpecifications specifications) {
        return getDomainListFromEntity(jpaRepository.findAll(specifications));
    }

    private List<Student> getDomainListFromEntity(List<StudentEntity> students) {
        return students.stream().map(mapper::toDomain).collect(Collectors.toList());
    }
}
