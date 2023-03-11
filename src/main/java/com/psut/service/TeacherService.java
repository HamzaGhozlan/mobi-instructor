package com.psut.service;

import com.psut.exception.RecordNotFoundException;
import com.psut.model.teacher.Teacher;
import com.psut.repository.JpaTeacherRepository;
import com.psut.repository.entity.MaterialEntity;
import com.psut.repository.entity.TeacherEntity;
import com.psut.repository.mapper.TeacherMapper;
import com.psut.repository.specification.TeacherSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final JpaTeacherRepository jpaRepository;
    private final TeacherMapper mapper;

    public Teacher findById(Long id) {
        TeacherEntity teacherEntity = validateExistence(id);
        return mapper.toDomain(teacherEntity);
    }

    public List<Teacher> findAll(Teacher example) {
        Example<TeacherEntity> example1 = Example.of(mapper.toEntity(example));
        List<TeacherEntity> teachers = jpaRepository.findAll(example1);
        return getDomainListFromEntities(teachers);
    }

    public List<Teacher> findAll(TeacherSpecifications specifications, Sort sort) {
        return getDomainListFromEntities(jpaRepository.findAll(specifications, sort));
    }

    @Transactional
    public Teacher save(Teacher teacher) {
        TeacherEntity teacherEntity = mapper.toEntity(teacher);
        teacherEntity.setReferenceForAllMaterials();
        teacherEntity = jpaRepository.save(teacherEntity);
        return mapper.toDomain(teacherEntity);
    }

    @Transactional
    public Teacher update(Teacher teacher) {
        validateExistence(teacher.getId());

        TeacherEntity teacherEntity = mapper.toEntity(teacher);
        teacherEntity.setReferenceForAllMaterials();
        teacherEntity = jpaRepository.save(teacherEntity);

        return mapper.toDomain(teacherEntity);
    }

    private TeacherEntity validateExistence(Long id) {
        return jpaRepository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
    }

    private List<Teacher> getDomainListFromEntities(List<TeacherEntity> teachers) {
        return teachers.stream().map(mapper::toDomain).collect(Collectors.toList());
    }
}
