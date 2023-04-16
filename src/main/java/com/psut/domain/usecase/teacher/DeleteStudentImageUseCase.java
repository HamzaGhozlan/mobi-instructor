package com.psut.domain.usecase.teacher;

import com.psut.exception.TechnicalValidationException;
import com.psut.service.StudentService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteStudentImageUseCase {
    private final StudentService studentService;

    public void execute(Long studentId) {
        byte[] image = studentService.findImage(studentId);
        if (image == null || image.length == 0) {
            throw new TechnicalValidationException("student.with.passed.id.does.not.have.image");
        }
        studentService.removeImage(studentId);
    }
}
