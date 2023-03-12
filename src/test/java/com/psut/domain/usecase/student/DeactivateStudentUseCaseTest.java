package com.psut.domain.usecase.student;

import com.psut.exception.TechnicalValidationException;
import com.psut.model.shared.UserStatus;
import com.psut.model.student.Student;
import com.psut.service.StudentService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeactivateStudentUseCaseTest {
    private static final Student ACTIVE_STUDENT = new Student();
    private static final Student DEACTIVATED_STUDENT = new Student();

    @Mock
    private StudentService service;
    @InjectMocks
    private DeactivateStudentUseCase useCase;

    @BeforeAll
    static void init() {
        ACTIVE_STUDENT.setStatus(UserStatus.ACTIVE);
        DEACTIVATED_STUDENT.setStatus(UserStatus.DEACTIVATED);
    }

    @Test
    void givenActiveStudent_whenExecute_thenShouldUpdateWithDeactivatedStatus() {
        when(service.findById(1L)).thenReturn(ACTIVE_STUDENT);
        useCase.execute(1L);
        verify(service).update(DEACTIVATED_STUDENT);
    }

    @Test
    void givenDeactivatedStudent_whenExecute_thenShouldThrowException() {
        when(service.findById(1L)).thenReturn(DEACTIVATED_STUDENT);
        assertThrows(TechnicalValidationException.class,
                () -> useCase.execute(1L));
    }
}