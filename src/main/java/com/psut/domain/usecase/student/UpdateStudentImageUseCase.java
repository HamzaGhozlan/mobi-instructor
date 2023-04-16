package com.psut.domain.usecase.student;

import com.psut.domain.validator.ImageValidator;
import com.psut.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
public class UpdateStudentImageUseCase {
    private final StudentService studentService;
    private final ImageValidator imageValidator;

    public byte[] execute(Long studentId, MultipartFile image) throws IOException {
        imageValidator.validate(image);
        return studentService.updateImage(studentId, image.getBytes());
    }
}
