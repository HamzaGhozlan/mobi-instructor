package com.psut.domain.usecase.teacher;

import com.psut.domain.validator.TeacherValidator;
import com.psut.exception.BusinessValidationException;
import com.psut.model.teacher.Teacher;
import com.psut.model.teacher.UpdateTeacherRequest;
import com.psut.service.TeacherService;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class UpdateTeacherUseCase {
    private final TeacherValidator teacherValidator;
    private final TeacherService teacherService;

    public Teacher execute(Long id, UpdateTeacherRequest updateRequest) {
        Teacher teacher = teacherService.findById(id);
        updateRequest.applyUpdatesOn(teacher);
        validate(teacher);
        return teacherService.update(teacher);
    }

    private void validate(Teacher teacher) {
        Set<String> violations = teacherValidator.validate(teacher);
        if (violations != null && !violations.isEmpty()) {
            throw new BusinessValidationException(violations);
        }
    }
}
