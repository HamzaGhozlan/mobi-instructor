package com.psut.domain.usecase.teacher;

import com.psut.domain.validator.TeacherValidator;
import com.psut.exception.BusinessValidationException;
import com.psut.model.teacher.Teacher;
import com.psut.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateTeacherUseCaseTest {
    private static final Teacher TEACHER = new Teacher();

    @Mock
    private TeacherValidator validator;
    @Mock
    private TeacherService service;
    @InjectMocks
    private CreateTeacherUseCase useCase;

    @Test
    void givenValidTeacherData_whenExecute_thenShouldInvokeSaveMethod() {
        when(validator.validate(TEACHER)).thenReturn(Collections.emptySet());
        useCase.execute(TEACHER);
        verify(service).save(TEACHER);
    }

    @Test
    void givenInvalidData_thenShouldThrowException() {
        when(validator.validate(TEACHER)).thenReturn(Collections.singleton("invalid"));
        assertThrows(BusinessValidationException.class,
                () -> useCase.execute(TEACHER));
    }
}