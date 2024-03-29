package com.psut.repository;

import com.psut.repository.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaTeacherRepository extends JpaRepository<TeacherEntity, Long>, JpaSpecificationExecutor<TeacherEntity> {
    @Modifying
    @Query("UPDATE #{#entityName} t SET t.rateAvg = ?2 WHERE t.id = ?1")
    void updateTeacherRateAvg(Long teacherId, Double rateAvg);

    @Modifying
    @Query("UPDATE #{#entityName} t SET t.description = ?2 WHERE t.id = ?1")
    String updateDescription(Long teacherId, String description);

    @Modifying
    @Query("UPDATE #{#entityName} t SET t.image = ?2 WHERE t.id = ?1")
    byte[] updateImage(Long teacherId, byte[] image);

    TeacherEntity findByUsernameAndPassword(String username, String password);
}
