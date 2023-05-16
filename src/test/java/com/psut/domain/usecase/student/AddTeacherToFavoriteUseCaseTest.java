package com.psut.domain.usecase.student;

import com.psut.exception.TechnicalValidationException;
import com.psut.model.teacher.Teacher;
import com.psut.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddTeacherToFavoriteUseCaseTest {
    private static final Long STUDENT_ID = 10L;
    private static final Long TEACHER_ID = 5L;
    private static final List<Teacher> NON_EMPTY_TEACHER_LIST = new ArrayList<>();
    private static final List<Teacher> EMPTY_TEACHER_LIST = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Teacher teacher = new Teacher();
        teacher.setId(TEACHER_ID);
        NON_EMPTY_TEACHER_LIST.add(teacher);
    }

    @Mock
    private StudentService service;
    @InjectMocks
    private AddTeacherToFavoriteUseCase useCase;

    @Test
    void givenTeacherInFavoriteList_whenExecute_thenShouldThrowException() {
        when(service.listFavoriteTeachers(STUDENT_ID)).thenReturn(NON_EMPTY_TEACHER_LIST);
        assertThrows(TechnicalValidationException.class, () -> useCase.execute(STUDENT_ID, TEACHER_ID));
    }

    @Test
    void givenTeacherNotInFavoriteList_whenExecute_thenShouldInvokeUpdateMethod() {
        when(service.listFavoriteTeachers(STUDENT_ID)).thenReturn(EMPTY_TEACHER_LIST);
        useCase.execute(STUDENT_ID, TEACHER_ID);
        verify(service).updateFavoriteTeachers(STUDENT_ID, List.of(TEACHER_ID));
    }
}