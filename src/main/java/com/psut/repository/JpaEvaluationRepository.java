package com.psut.repository;

import com.psut.repository.entity.EvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaEvaluationRepository extends JpaRepository<EvaluationEntity, Long>, JpaSpecificationExecutor<EvaluationEntity> {
    void deleteById(Long id);

    @Query("SELECT AVG(e.rate) FROM EvaluationEntity e WHERE e.teacher.id = :teacherId")
    Double fetchTeacherRateAvg(@Param("teacherId") Long teacherId);
}
