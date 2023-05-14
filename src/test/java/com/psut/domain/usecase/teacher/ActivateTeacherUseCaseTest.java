package com.psut.domain.usecase.teacher;

import com.psut.exception.TechnicalValidationException;
import com.psut.model.shared.UserStatus;
import com.psut.model.teacher.Teacher;
import com.psut.service.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActivateTeacherUseCaseTest {
    private static final Teacher ACTIVE_TEACHER = new Teacher();
    private static final Teacher DEACTIVATED_TEACHER = new Teacher();

    @Mock
    private TeacherService service;
    @InjectMocks
    private ActivateTeacherUseCase useCase;

    @BeforeEach
    void setUp() {
        ACTIVE_TEACHER.setStatus(UserStatus.ACTIVE);
        DEACTIVATED_TEACHER.setStatus(UserStatus.DEACTIVATED);
    }

    @Test
    void givenActiveTeacher_whenExecute_thenShouldThrowException() {
        when(service.findById(5L)).thenReturn(ACTIVE_TEACHER);
        assertThrows(TechnicalValidationException.class,
                () -> useCase.execute(5L));
    }

    @Test
    void givenDeactivatedTeacher_whenExecute_thenShouldInvokeUpdateMethod() {
        when(service.findById(5L)).thenReturn(DEACTIVATED_TEACHER);
        when(service.update(DEACTIVATED_TEACHER)).thenReturn(ACTIVE_TEACHER);
        Teacher teacher = useCase.execute(5L);
        verify(service).update(DEACTIVATED_TEACHER);
        assertEquals(ACTIVE_TEACHER, teacher);
    }
}