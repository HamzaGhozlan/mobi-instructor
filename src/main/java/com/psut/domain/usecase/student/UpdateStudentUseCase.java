package com.psut.domain.usecase.student;

import com.psut.exception.BusinessValidationException;
import com.psut.model.student.Student;
import com.psut.model.student.UpdateStudentRequest;
import com.psut.service.StudentService;
import com.psut.domain.validator.StudentValidator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class UpdateStudentUseCase {
    private final StudentService studentService;
    private final StudentValidator studentValidator;

    public Student execute(Long id, UpdateStudentRequest updateRequest) {
        Student student = studentService.findById(id);
        updateRequest.applyUpdatesOn(student);
        validate(student);
        return studentService.update(student);
    }

    private void validate(Student student) {
        Set<String> violations = studentValidator.validate(student);
        if (!violations.isEmpty()) {
            throw new BusinessValidationException(violations);
        }
    }
}
