package com.psut.repository;

import com.psut.repository.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaTeacherRepository extends JpaRepository<TeacherEntity, Long>, JpaSpecificationExecutor<TeacherEntity> {
    @Query("UPDATE TeacherEntity t SET t.rateAvg = :rateAvg WHERE t.id = :teacherId")
    Double updateTeacherRateAvg(@Param("id") Long teacherId, @Param("rateAvg") Double rateAvg);
}
