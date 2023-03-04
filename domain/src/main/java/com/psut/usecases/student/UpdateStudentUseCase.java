package com.psut.usecases.student;

import com.psut.exceptions.BusinessValidationException;
import com.psut.models.student.Student;
import com.psut.models.student.UpdateStudentRequest;
import com.psut.repositories.StudentRepository;
import com.psut.validators.StudentValidator;
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
