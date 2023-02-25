package com.psut.repositories.adapters;

import com.psut.repositories.JpaTeacherRepository;
import com.psut.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherRepositoryAdapter implements TeacherRepository {
    private final JpaTeacherRepository repository;
}
