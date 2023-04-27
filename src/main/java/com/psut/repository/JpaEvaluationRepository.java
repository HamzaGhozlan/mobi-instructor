package com.psut.repository;

import com.psut.repository.entity.EvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface JpaEvaluationRepository extends JpaRepository<EvaluationEntity, Long>, JpaSpecificationExecutor<EvaluationEntity> {
    EvaluationEntity findByTeacherIdAndStudentId(Long teacherId, Long studentId);

    void deleteById(Long id);

    @Query("SELECT AVG(e.rate) FROM #{#entityName} e WHERE e.teacher.id = ?1")
    Double getTeacherRateAvg(Long teacherId);
}
