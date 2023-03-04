package com.psut.usecase.student;

import com.psut.exception.BusinessValidationException;
import com.psut.model.shared.UserStatus;
import com.psut.model.student.Student;
import com.psut.repository.impl.StudentRepository;
import com.psut.validator.StudentValidator;
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
