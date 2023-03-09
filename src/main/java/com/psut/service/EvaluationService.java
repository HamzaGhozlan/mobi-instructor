package com.psut.service;

import com.psut.exception.RecordNotFoundException;
import com.psut.model.evaluation.Evaluation;
import com.psut.repository.JpaEvaluationRepository;
import com.psut.repository.JpaStudentRepository;
import com.psut.repository.JpaTeacherRepository;
import com.psut.repository.entity.EvaluationEntity;
import com.psut.repository.entity.StudentEntity;
import com.psut.repository.entity.TeacherEntity;
import com.psut.repository.mapper.EvaluationMapper;
import com.psut.repository.specification.EvaluationSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EvaluationService {
    private final JpaEvaluationRepository jpaRepository;
    private final JpaStudentRepository jpaStudentRepository;
    private final JpaTeacherRepository jpaTeacherRepository;
    private final EvaluationMapper mapper;

    public List<Evaluation> findAll(EvaluationSpecifications specifications) {
        return jpaRepository.findAll(specifications)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    public Evaluation findById(Long id) {
        EvaluationEntity evaluationEntity = validateEvaluationExistence(id);
        return mapper.toDomain(evaluationEntity);
    }

    @Transactional
    public Evaluation save(Evaluation evaluation) {
        EvaluationEntity evaluationEntity = mapper.toEntity(evaluation);
        evaluationEntity.setStudent(validateStudentExistence(evaluation.getTeacherId()));
        evaluationEntity.setTeacher(validateTeacherExistence(evaluation.getStudentId()));

        evaluationEntity = jpaRepository.save(evaluationEntity);
        Evaluation savedEvaluation = mapper.toDomain(evaluationEntity);
        updateTeacherRateAvg(savedEvaluation.getTeacherId());

        return savedEvaluation;
    }

    @Transactional
    public Evaluation update(Evaluation evaluation) {
        validateEvaluationExistence(evaluation.getId());
        EvaluationEntity evaluationEntity = mapper.toEntity(evaluation);
        evaluationEntity.setTeacher(validateTeacherExistence(evaluation.getTeacherId()));
        evaluationEntity.setStudent(validateStudentExistence(evaluation.getStudentId()));

        evaluationEntity = jpaRepository.save(evaluationEntity);
        Evaluation savedEvaluation = mapper.toDomain(evaluationEntity);
        updateTeacherRateAvg(savedEvaluation.getTeacherId());

        return savedEvaluation;
    }

    @Transactional
    public void delete(Evaluation evaluation) {
        jpaRepository.deleteById(evaluation.getId());
        updateTeacherRateAvg(evaluation.getTeacherId());
    }

    private void updateTeacherRateAvg(Long teacherId) {
        jpaTeacherRepository.updateTeacherRateAvg(
                teacherId,
                jpaRepository.fetchTeacherRateAvg(teacherId)
        );
    }

    private EvaluationEntity validateEvaluationExistence(Long evaluationId) {
        return jpaRepository.findById(evaluationId)
                .orElseThrow(RecordNotFoundException::new);
    }

    private StudentEntity validateStudentExistence(Long studentId) {
        return jpaStudentRepository.findById(studentId)
                .orElseThrow(RecordNotFoundException::new);
    }

    private TeacherEntity validateTeacherExistence(Long teacherId) {
        return jpaTeacherRepository.findById(teacherId)
                .orElseThrow(RecordNotFoundException::new);
    }
}
