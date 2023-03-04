package com.psut.usecase.student;

import com.psut.exception.BusinessValidationException;
import com.psut.model.student.Student;
import com.psut.model.student.UpdateStudentRequest;
import com.psut.repository.impl.StudentRepository;
import com.psut.validator.StudentValidator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class UpdateStudentUseCase {
    private final StudentRepository studentRepository;
    private final StudentValidator studentValidator;

    public Student execute(Student student, UpdateStudentRequest updateRequest) {
        updateRequest.applyUpdatesOn(student);
        validate(student);
        return studentRepository.save(student);
    }

    private void validate(Student student) {
        Set<String> violations = studentValidator.validate(student);
        if (!violations.isEmpty()) {
            throw new BusinessValidationException(violations);
        }
    }
}
