package com.psut.service;

import com.psut.exception.RecordNotFoundException;
import com.psut.model.evaluation.Evaluation;
import com.psut.repository.JpaEvaluationRepository;
import com.psut.repository.JpaTeacherRepository;
import com.psut.repository.entity.EvaluationEntity;
import com.psut.repository.mapper.EvaluationMapper;
import com.psut.repository.specification.EvaluationSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvaluationService {
    private final JpaEvaluationRepository jpaRepository;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final JpaTeacherRepository jpaTeacherRepository;
    private final EvaluationMapper mapper;

    public Page<Evaluation> findAll(EvaluationSpecifications specifications, Pageable pageable) {
        return mapper.toDomain(jpaRepository.findAll(specifications, pageable));
    }

    public Evaluation findById(Long id) {
        EvaluationEntity evaluationEntity = validateExistence(id);
        return mapper.toDomain(evaluationEntity);
    }

    public Optional<Evaluation> findByTeacherAndStudent(Long teacherId, Long studentId) {
        EvaluationEntity evaluationEntity = jpaRepository.findByTeacherIdAndStudentId(teacherId, studentId);
        return Optional.of(mapper.toDomain(evaluationEntity));
    }

    @Transactional
    public Evaluation save(Evaluation evaluation) {
        EvaluationEntity evaluationEntity = mapper.toEntity(evaluation);
        evaluationEntity.setStudent(studentService.validateExistence(evaluation.getTeacherId()));
        evaluationEntity.setTeacher(teacherService.validateExistence(evaluation.getStudentId()));

        evaluationEntity = jpaRepository.save(evaluationEntity);
        Evaluation savedEvaluation = mapper.toDomain(evaluationEntity);
        fetchTeacherRateAvg(savedEvaluation.getTeacherId());

        return savedEvaluation;
    }

    @Transactional
    public Evaluation update(Evaluation evaluation) {
        validateExistence(evaluation.getId());
        EvaluationEntity evaluationEntity = mapper.toEntity(evaluation);
        evaluationEntity.setTeacher(teacherService.validateExistence(evaluation.getTeacherId()));
        evaluationEntity.setStudent(studentService.validateExistence(evaluation.getStudentId()));

        evaluationEntity = jpaRepository.save(evaluationEntity);
        Evaluation savedEvaluation = mapper.toDomain(evaluationEntity);
        fetchTeacherRateAvg(savedEvaluation.getTeacherId());

        return savedEvaluation;
    }

    @Transactional
    public void delete(Long id) {
        EvaluationEntity evaluation = validateExistence(id);
        jpaRepository.deleteById(id);
        fetchTeacherRateAvg(evaluation.getTeacher().getId());
    }

    //TODO: should not be executed under multiThreading
    private void fetchTeacherRateAvg(Long teacherId) {
        jpaTeacherRepository.updateTeacherRateAvg(
                teacherId,
                jpaRepository.getTeacherRateAvg(teacherId)
        );
    }

    private EvaluationEntity validateExistence(Long evaluationId) {
        return jpaRepository.findById(evaluationId)
                .orElseThrow(RecordNotFoundException::new);
    }
}
