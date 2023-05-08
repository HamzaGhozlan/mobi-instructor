package com.psut.repository;

import com.psut.repository.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaStudentRepository extends JpaRepository<StudentEntity, Long>, JpaSpecificationExecutor<StudentEntity> {
    @Modifying
    @Query("UPDATE #{#entityName} t SET t.image = ?2 WHERE t.id = ?1")
    byte[] updateImage(Long studentId, byte[] image);

    StudentEntity findByUsernameAndPassword(String username, String password);
}
