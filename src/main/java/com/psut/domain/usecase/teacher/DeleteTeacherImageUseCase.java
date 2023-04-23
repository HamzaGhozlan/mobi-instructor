package com.psut.domain.usecase.teacher;

import com.psut.exception.TechnicalValidationException;
import com.psut.service.TeacherService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteTeacherImageUseCase {
    private final TeacherService teacherService;

    public void execute(Long teacherId) {
        byte[] image = teacherService.findImage(teacherId);
        if (image == null) {
            throw new TechnicalValidationException("teacher.with.passed.id.does.not.have.image");
        }
        teacherService.deleteImage(teacherId);
    }
}
