package com.psut.domain.usecase.student;

import com.psut.domain.validator.StudentValidator;
import com.psut.exception.BusinessValidationException;
import com.psut.model.student.Student;
import com.psut.model.student.UpdateStudentRequest;
import com.psut.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateStudentUseCaseTest {
    @Mock
    private StudentValidator validator;
    @Mock
    private StudentService service;
    @InjectMocks
    private UpdateStudentUseCase useCase;

    @Test
    void givenValidUpdateRequest_whenExecute_thenShouldInvokeUpdateMethod() {
        Student oldStudent = getStudent();
        UpdateStudentRequest updateRequest = UpdateStudentRequest.builder()
                .firstName("updatedFirstName")
                .lastName("updatedLastName")
                .email("updatedEmail")
                .username("updatedUsername")
                .password("updatedPassword")
                .build();
        Student updatedStudent = updateRequest.applyUpdatesOn(getStudent());

        when(service.findById(1L)).thenReturn(oldStudent);
        when(validator.validate(updatedStudent)).thenReturn(Collections.emptySet());
        useCase.execute(1L, updateRequest);

        verify(service).update(updatedStudent);
    }

    @Test
    void givenInvalidUpdateRequest_whenExecute_thenShouldThrowException() {
        when(service.findById(1L)).thenReturn(new Student());
        when(validator.validate(any())).thenReturn(Collections.singleton("invalid"));
        assertThrows(BusinessValidationException.class,
                () -> useCase.execute(1L, UpdateStudentRequest.builder().build()));
    }

    private Student getStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("firstName");
        student.setLastName("lastName");
        student.setEmail("email");
        student.setUsername("username");
        student.setPassword("password");

        return student;
    }
}