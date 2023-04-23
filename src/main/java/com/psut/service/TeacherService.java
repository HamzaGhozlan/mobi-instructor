package com.psut.service;

import com.psut.exception.RecordNotFoundException;
import com.psut.model.teacher.Teacher;
import com.psut.repository.JpaTeacherRepository;
import com.psut.repository.entity.TeacherEntity;
import com.psut.repository.mapper.TeacherMapper;
import com.psut.repository.specification.TeacherSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return mapper.toDomain(teachers);
    }

    public Page<Teacher> findAll(TeacherSpecifications specifications, Pageable pageable) {
        return mapper.toDomain(jpaRepository.findAll(specifications, pageable));
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

    public String updateDescription(Long teacherId, String description) {
        validateExistence(teacherId);
        return jpaRepository.updateDescription(teacherId, description);
    }

    public byte[] findImage(Long teacherId) {
        return validateExistence(teacherId).getImage();
    }

    public byte[] updateImage(Long teacherId, byte[] image) {
        validateExistence(teacherId);
        return jpaRepository.updateImage(teacherId, image);
    }

    public void deleteImage(Long teacherId) {
        validateExistence(teacherId);
        jpaRepository.updateImage(teacherId, null);
    }

    public TeacherEntity validateExistence(Long id) {
        return jpaRepository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
    }
}
