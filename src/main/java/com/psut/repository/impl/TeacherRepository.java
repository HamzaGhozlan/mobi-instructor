package com.psut.repository.impl;

import com.psut.repository.JpaTeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherRepository {
    private final JpaTeacherRepository repository;
}
