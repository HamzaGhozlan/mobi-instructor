package com.psut.domain.usecase.teacher;

import com.psut.domain.validator.TeacherValidator;
import com.psut.exception.BusinessValidationException;
import com.psut.model.shared.Gender;
import com.psut.model.teacher.Teacher;
import com.psut.model.teacher.TeachingWay;
import com.psut.model.teacher.UpdateTeacherRequest;
import com.psut.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateTeacherUseCaseTest {
    @Mock
    private TeacherValidator validator;
    @Mock
    private TeacherService service;
    @InjectMocks
    private UpdateTeacherUseCase useCase;

    @Test
    void givenValidUpdateRequest_whenExecute_thenShouldInvokeUpdateMethod() {
        Teacher oldTeacher = getTeacher();
        UpdateTeacherRequest updateRequest = UpdateTeacherRequest.builder()
                .firstName("updatedFirstName")
                .lastName("updatedLastName")
                .username("updatedUsername")
                .password("updatedPassword")
                .phoneNumber("updatedPhoneNumber")
                .email("updatedEmail")
                .city("updatedCity")
                .teachingWay(List.of(TeachingWay.ONSITE, TeachingWay.ONLINE))
                .targetedStudents(List.of(Gender.FEMALE, Gender.MALE))
                .build();
        Teacher updatedTeacher = updateRequest.applyUpdatesOn(getTeacher());

        when(service.findById(1L)).thenReturn(oldTeacher);
        when(validator.validate(updatedTeacher)).thenReturn(Collections.emptySet());
        useCase.execute(1L, updateRequest);

        verify(service).update(updatedTeacher);
    }

    @Test
    void givenInvalidData_whenExecute_thenShouldThrowException() {
        when(service.findById(1L)).thenReturn(new Teacher());
        when(validator.validate(any())).thenReturn(Collections.singleton("invalid"));
        assertThrows(BusinessValidationException.class,
                () -> useCase.execute(1L, UpdateTeacherRequest.builder().build()));
    }

    private Teacher getTeacher() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setFirstName("firstName");
        teacher.setLastName("lastName");
        teacher.setUsername("username");
        teacher.setPassword("password");
        teacher.setPhoneNumber("0790000000");
        teacher.setEmail("email@gmail.com");
        teacher.setCity("city");
        teacher.setTeachingWay(Collections.singletonList(TeachingWay.ONLINE));
        teacher.setTargetedStudents(Collections.singletonList(Gender.MALE));
        return teacher;
    }
}