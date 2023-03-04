package com.psut.validator;

import com.psut.domain.validator.PasswordValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PasswordValidatorTest {
    private PasswordValidator passwordValidator;
    private Set<String> violations;

    @BeforeEach
    void setUp() {
        passwordValidator = new PasswordValidator();
        violations = new HashSet();
    }

    @Test
    void givenValidPassword_whenValidate_thenShouldReturnEmptyViolations() {
        passwordValidator.validate("aaaa@A-5", violations);
        assertEquals(true, violations.isEmpty());
    }

    @Test
    void givenShortPassword_whenValidate_thenShouldReturnViolations() {
        passwordValidator.validate("a@A-5", violations);
        assertEquals(false, violations.isEmpty());
    }

    @Test
    void givenPasswordWithoutLowerCase_whenValidate_thenShouldReturnViolation() {
        passwordValidator.validate("A@5", violations);
        assertEquals(false, violations.isEmpty());
    }

    @Test
    void givenPasswordWithoutUpperCase_whenValidate_thenShouldReturnViolation() {
        passwordValidator.validate("a@5", violations);
        assertEquals(false, violations.isEmpty());
    }

    @Test
    void givenPasswordWithoutNumber_whenValidate_thenShouldReturnViolation() {
        passwordValidator.validate("A@a", violations);
        assertEquals(false, violations.isEmpty());
    }

    @Test
    void givenPasswordWithoutSpecialCharacter_whenValidate_thenShouldReturnViolation() {
        passwordValidator.validate("A5a", violations);
        assertEquals(false, violations.isEmpty());
    }
}