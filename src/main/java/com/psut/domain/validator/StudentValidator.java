package com.psut.domain.validator;

import com.psut.model.student.Student;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class StudentValidator extends AbstractValidator {
    private final PasswordValidator passwordValidator;
    private final UsernameValidator usernameValidator;

    public Set<String> validate(Student student) {
        violations = new HashSet();
        validateNotNull(student.getGender(), "gender");
        validateNotBlank(student.getFirstName(), "first.name");
        validateNotBlank(student.getLastName(), "last.name");
        validateEmail(student.getEmail());
        usernameValidator.validate(student, violations);
        passwordValidator.validate(student.getPassword(), violations);
        return violations;
    }

    private void validateEmail(String email) {
        if (StringUtils.isBlank(email) || !email.contains("@")) {
            violations.add("invalid.email");
        }
    }
}
