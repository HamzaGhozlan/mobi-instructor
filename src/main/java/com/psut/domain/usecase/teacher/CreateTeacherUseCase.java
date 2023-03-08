package com.psut.domain.usecase.teacher;

import com.psut.domain.validator.TeacherValidator;
import com.psut.exception.BusinessValidationException;
import com.psut.model.shared.UserStatus;
import com.psut.model.teacher.Teacher;
import com.psut.service.TeacherService;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
public class CreateTeacherUseCase {
    private final TeacherValidator teacherValidator;
    private final TeacherService teacherService;

    public Teacher execute(Teacher teacher) {
        validate(teacher);
        teacher.setStatus(UserStatus.ACTIVE);
        return teacherService.save(teacher);
    }

    private void validate(Teacher teacher) {
        Set<String> violations = teacherValidator.validate(teacher);
        if(Objects.nonNull(violations) && !violations.isEmpty()){
            throw new BusinessValidationException(violations);
        }
    }
}
