package com.psut.domain.validator;

import com.psut.model.student.Student;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class StudentValidator {
    private final UsernameValidator usernameValidator;
    private final PasswordValidator passwordValidator;

    public Set<String> validate(Student student) {
        Set<String> violations = new ValidationHelper()
                .validateNotNull(student.getGender(), "gender")
                .validateNotBlank(student.getFirstName(), "first.name")
                .validateNotBlank(student.getLastName(), "last.name")
                .getViolations();

        validateEmail(student.getEmail(), violations);
        usernameValidator.validate(student, violations);
        passwordValidator.validate(student.getPassword(), violations);
        return violations;
    }

    private void validateEmail(String email, Set<String> violations) {
        if (StringUtils.isBlank(email) || !email.contains("@")) {
            violations.add("invalid.email");
        }
    }
}
