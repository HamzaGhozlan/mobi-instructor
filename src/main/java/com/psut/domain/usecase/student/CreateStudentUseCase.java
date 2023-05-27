package com.psut.domain.usecase.student;

import com.psut.domain.validator.StudentValidator;
import com.psut.exception.BusinessValidationException;
import com.psut.model.shared.UserStatus;
import com.psut.model.student.Student;
import com.psut.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@RequiredArgsConstructor
public class CreateStudentUseCase {
    private final StudentValidator studentValidator;
    private final StudentService studentService;
    private final PasswordEncoder passwordEncoder;

    public Student execute(Student student) {
        validate(student);
        student.setStatus(UserStatus.ACTIVE);
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentService.save(student);
    }

    private void validate(Student student) {
        Set<String> violations = studentValidator.validate(student);
        if (!violations.isEmpty()) {
            throw new BusinessValidationException(violations);
        }
    }
}
