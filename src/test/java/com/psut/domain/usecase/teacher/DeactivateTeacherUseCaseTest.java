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
class DeactivateTeacherUseCaseTest {
    private static final Teacher ACTIVE_TEACHER = new Teacher();
    private static final Teacher DEACTIVATED_TEACHER = new Teacher();

    @Mock
    private TeacherService service;
    @InjectMocks
    private DeactivateTeacherUseCase useCase;

    @BeforeEach
    void setUp() {
        ACTIVE_TEACHER.setStatus(UserStatus.ACTIVE);
        DEACTIVATED_TEACHER.setStatus(UserStatus.DEACTIVATED);
    }

    @Test
    void givenDeactivatedTeacher_whenExecute_thenShouldThrowException() {
        when(service.findById(5L)).thenReturn(DEACTIVATED_TEACHER);
        assertThrows(TechnicalValidationException.class,
                () -> useCase.execute(5L));
    }

    @Test
    void givenActiveTeacher_whenExecute_thenShouldInvokeUpdateMethod() {
        when(service.findById(5L)).thenReturn(ACTIVE_TEACHER);
        when(service.update(ACTIVE_TEACHER)).thenReturn(DEACTIVATED_TEACHER);
        Teacher teacher = useCase.execute(5L);
        verify(service).update(ACTIVE_TEACHER);
        assertEquals(DEACTIVATED_TEACHER, teacher);
    }
}