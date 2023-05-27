package com.psut.domain.usecase.student;

import com.psut.domain.validator.StudentValidator;
import com.psut.exception.BusinessValidationException;
import com.psut.model.student.Student;
import com.psut.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateStudentUseCaseTest {
    private static final Student STUDENT = new Student();

    @Mock
    private StudentValidator validator;
    @Mock
    private StudentService service;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private CreateStudentUseCase useCase;

    @Test
    void givenValidData_whenExecute_thenShouldInvokeSaveMethod() {
        when(validator.validate(STUDENT)).thenReturn(Collections.emptySet());
        useCase.execute(STUDENT);
        verify(service).save(STUDENT);
    }

    @Test
    void givenInvalidStudentData_whenExecute_thenShouldThrowException() {
        when(validator.validate(STUDENT)).thenReturn(Collections.singleton("invalid"));
        assertThrows(BusinessValidationException.class,
                () -> useCase.execute(STUDENT));
    }
}