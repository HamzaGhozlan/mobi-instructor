package com.psut.service;

import com.psut.exception.RecordNotFoundException;
import com.psut.model.student.Student;
import com.psut.repository.JpaStudentRepository;
import com.psut.repository.entity.StudentEntity;
import com.psut.repository.mapper.StudentMapper;
import com.psut.repository.specification.StudentSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final JpaStudentRepository jpaRepository;
    private final StudentMapper mapper;

    public Student findById(Long id) {
        StudentEntity studentEntity = validateExistence(id);
        return mapper.toDomain(studentEntity);
    }

    public List<Student> findAll(Student example) {
        Example<StudentEntity> example1 = Example.of(mapper.toEntity(example));
        return getDomainListFromEntities(jpaRepository.findAll(example1));
    }

    public List<Student> findAll(StudentSpecifications specifications, Sort sort) {
        return getDomainListFromEntities(jpaRepository.findAll(specifications, sort));
    }

    @Transactional
    public Student save(Student student) {
        StudentEntity studentEntity = mapper.toEntity(student);
        studentEntity = jpaRepository.save(studentEntity);
        return mapper.toDomain(studentEntity);
    }

    @Transactional
    public Student update(Student student) {
        validateExistence(student.getId());
        StudentEntity studentEntity = mapper.toEntity(student);
        studentEntity = jpaRepository.save(studentEntity);
        return mapper.toDomain(studentEntity);
    }

    private StudentEntity validateExistence(Long id) {
        return jpaRepository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
    }

    private List<Student> getDomainListFromEntities(List<StudentEntity> students) {
        return students.stream().map(mapper::toDomain).collect(Collectors.toList());
    }
}
