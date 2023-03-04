package com.psut.repository;

import com.psut.repository.entities.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTeacherRepository extends JpaRepository<TeacherEntity, Long> {
}
