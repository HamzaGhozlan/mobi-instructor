package com.psut.repositories;

import com.psut.repositories.entities.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTeacherRepository extends JpaRepository<TeacherEntity, Long> {
}
