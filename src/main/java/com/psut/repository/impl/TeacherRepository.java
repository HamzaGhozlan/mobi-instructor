package com.psut.repository.impl;

import com.psut.exception.RecordNotFoundException;
import com.psut.model.teacher.Teacher;
import com.psut.repository.JpaTeacherRepository;
import com.psut.repository.entity.TeacherEntity;
import com.psut.repository.mapper.TeacherMapper;
import com.psut.repository.specification.TeacherSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TeacherRepository {
    private final JpaTeacherRepository jpaRepository;
    private final TeacherMapper mapper;

    public Teacher findById(Long id) {
        TeacherEntity teacherEntity = jpaRepository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
        return mapper.toDomain(teacherEntity);
    }

    public List<Teacher> findAll(Teacher example) {
        Example<TeacherEntity> example1 = Example.of(mapper.toEntity(example));
        List<TeacherEntity> teachers = jpaRepository.findAll(example1);
        return getDomainListFromEntities(teachers);
    }

    public List<Teacher> findAll(TeacherSpecifications specifications){
        return getDomainListFromEntities(jpaRepository.findAll(specifications));
    }

    public Teacher save(Teacher teacher) {
        TeacherEntity teacherEntity = mapper.toEntity(teacher);
        teacherEntity = jpaRepository.save(teacherEntity);
        return mapper.toDomain(teacherEntity);
    }

    public Teacher update(Teacher teacher) {
        if(Objects.isNull(jpaRepository.findById(teacher.getId()))){
            throw new RecordNotFoundException();
        }
        TeacherEntity teacherEntity = mapper.toEntity(teacher);
        teacherEntity = jpaRepository.save(teacherEntity);
        return mapper.toDomain(teacherEntity);
    }

    private List<Teacher> getDomainListFromEntities(List<TeacherEntity> teachers) {
        return teachers.stream().map(mapper::toDomain).collect(Collectors.toList());
    }
}
