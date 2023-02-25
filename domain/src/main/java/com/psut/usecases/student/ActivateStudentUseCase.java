package com.psut.usecases.student;

import com.psut.exceptions.TechnicalValidationException;
import com.psut.models.shared.UserStatus;
import com.psut.models.student.Student;
import com.psut.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ActivateStudentUseCase {
    private final StudentRepository repository;

    public Student execute(Student student) {
        validate(student);
        student.setStatus(UserStatus.ACTIVE);
        return repository.save(student);
    }

    private void validate(Student student) {
        if (UserStatus.ACTIVE.equals(student.getStatus())) {
            throw new TechnicalValidationException("student.is.already.active");
        }
    }
}
