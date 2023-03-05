package com.psut.domain.usecase.teacher;

import com.psut.domain.validator.TeacherValidator;
import com.psut.exception.BusinessValidationException;
import com.psut.model.teacher.Teacher;
import com.psut.model.teacher.UpdateTeacherRequest;
import com.psut.repository.impl.TeacherRepository;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
public class UpdateTeacherUseCase {
    private final TeacherValidator teacherValidator;
    private final TeacherRepository teacherRepository;

    public Teacher execute(Teacher teacher, UpdateTeacherRequest updateRequest) {
        updateRequest.applyUpdatesOn(teacher);
        validate(teacher);
        return teacherRepository.update(teacher);
    }

    private void validate(Teacher teacher) {
        Set<String> violations = teacherValidator.validate(teacher);
        if(Objects.nonNull(violations) && !violations.isEmpty()){
            throw new BusinessValidationException(violations);
        }
    }
}
