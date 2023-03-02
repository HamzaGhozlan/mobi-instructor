package com.psut.repositories.adapters;

import com.psut.exceptions.RecordNotFoundException;
import com.psut.models.student.Student;
import com.psut.repositories.JpaStudentRepository;
import com.psut.repositories.StudentRepository;
import com.psut.repositories.entities.StudentEntity;
import com.psut.repositories.mappers.StudentMapper;
import com.psut.repositories.specifications.StudentSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
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
