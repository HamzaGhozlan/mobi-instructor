package com.psut.usecases.students;

import com.psut.exceptions.TechnicalValidationException;
import com.psut.models.shared.UserStatus;
import com.psut.models.student.Student;
import com.psut.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeactivateStudentUseCase {
    private final StudentRepository repository;

    public Student execute(Student student) {
        validate(student);
        student.setStatus(UserStatus.DEACTIVATED);
        return repository.save(student);
    }

    private void validate(Student student) {
        if (UserStatus.DEACTIVATED.equals(student.getStatus())) {
            throw new TechnicalValidationException("student.is.already.deactivated");
        }
    }
}
