package com.psut.domain.usecase.teacher;

import com.psut.exception.TechnicalValidationException;
import com.psut.model.shared.UserStatus;
import com.psut.model.teacher.Teacher;
import com.psut.service.TeacherService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ActivateTeacherUseCase {
    private final TeacherService service;

    public Teacher execute(Long id) {
        Teacher teacher = service.findById(id);
        validate(teacher);
        teacher.setStatus(UserStatus.ACTIVE);
        return service.update(teacher);
    }

    private void validate(Teacher teacher) {
        if (teacher.getStatus() == UserStatus.ACTIVE) {
            throw new TechnicalValidationException("teacher.is.already.active");
        }
    }
}
