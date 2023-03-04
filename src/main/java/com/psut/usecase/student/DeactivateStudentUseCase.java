package com.psut.usecase.student;

import com.psut.exception.TechnicalValidationException;
import com.psut.model.shared.UserStatus;
import com.psut.model.student.Student;
import com.psut.repository.impl.StudentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeactivateStudentUseCase {
    private final StudentRepository studentRepository;

    public Student execute(Student student) {
        validate(student);
        student.setStatus(UserStatus.DEACTIVATED);
        return studentRepository.save(student);
    }

    private void validate(Student student) {
        if (UserStatus.DEACTIVATED.equals(student.getStatus())) {
            throw new TechnicalValidationException("student.is.already.deactivated");
        }
    }
}
