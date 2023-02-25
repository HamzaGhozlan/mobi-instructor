package com.psut.usecases.student;

import com.psut.exceptions.BusinessValidationException;
import com.psut.models.shared.UserStatus;
import com.psut.models.student.Student;
import com.psut.repositories.StudentRepository;
import com.psut.validators.StudentValidator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class CreateStudentUseCase {
    private final StudentValidator studentValidator;
    private final StudentRepository studentRepository;

    public Student execute(Student student) {
        validate(student);
        student.setStatus(UserStatus.ACTIVE);
        return studentRepository.save(student);
    }

    private void validate(Student student) {
        Set<String> violations = studentValidator.validate(student);
        if (!violations.isEmpty()) {
            throw new BusinessValidationException(violations);
        }
    }
}
