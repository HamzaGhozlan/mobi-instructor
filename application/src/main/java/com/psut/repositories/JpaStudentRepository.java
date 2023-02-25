package com.psut.repositories;

import com.psut.repositories.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaStudentRepository extends JpaRepository<StudentEntity, Long> {
}
