package com.psut.domain.usecase.teacher;

import com.psut.domain.validator.ImageValidator;
import com.psut.exception.BusinessValidationException;
import com.psut.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@RequiredArgsConstructor
public class UpdateTeacherImageUseCase {
    private final TeacherService teacherService;
    private final ImageValidator imageValidator;

    public byte[] execute(Long teacherId, MultipartFile image) throws IOException {
        Set<String> violations = imageValidator.validate(image);
        if (!violations.isEmpty()) {
            throw new BusinessValidationException(violations);
        }
        return teacherService.updateImage(teacherId, image.getBytes());
    }
}
