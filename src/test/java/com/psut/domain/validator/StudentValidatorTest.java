package com.psut.domain.validator;

import com.psut.model.shared.Gender;
import com.psut.model.student.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentValidatorTest {
    private static final String USERNAME = "username";
    private static final String PASSWORD = "P@ssw0rd";

    @Mock
    private UsernameValidator usernameValidator;
    @Mock
    private PasswordValidator passwordValidator;
    @InjectMocks
    private StudentValidator studentValidator;

    @Test
    void givenValidStudent_whenExecute_thenShouldInvokePasswordValidatorAndUsernameValidatorAndReturnEmptyViolations() {
        Student student = getStudent();
        Set<String> violations = studentValidator.validate(student);
        verify(usernameValidator).validate(eq(student), any());
        verify(passwordValidator).validate(eq(PASSWORD), any());
        assertEquals(Collections.emptySet(), violations);
    }

    @Test
    void givenStudentWithInvalidEmail_whenExecute_thenShouldReturnNonEmptyViolations() {
        Student student = getStudent();
        student.setEmail("email");
        Set<String> violations = studentValidator.validate(student);
        assertEquals(Collections.singleton("invalid.email"), violations);
    }

    private Student getStudent() {
        Student student = new Student();
        student.setUsername(USERNAME);
        student.setPassword(PASSWORD);
        student.setFirstName("hamza");
        student.setLastName("ghozlan");
        student.setGender(Gender.MALE);
        student.setEmail("hamza.ghozlan@gmail.com");
        return student;
    }
}