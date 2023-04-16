package com.psut.domain.validator;

import com.psut.exception.BusinessValidationException;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ImageValidator {
    private static final List<String> validTypes = List.of("png", "jpg", "jpeg");

    public void validate(MultipartFile image) {
        Set<String> violations = new HashSet<>();
        validateSize(image, violations);
        validateContentType(image, violations);

        if (!violations.isEmpty()) {
            throw new BusinessValidationException(violations);
        }
    }

    private void validateSize(MultipartFile image, Set<String> violations) {
        if (image.getSize() == 0) {
            violations.add("can.not.add.empty.image");
        }
    }

    private void validateContentType(MultipartFile image, Set<String> violations) {
        final String contentType = image.getContentType();
        if (!validTypes.contains(contentType)) {
            violations.add(String.format("invalid content type (%s) for the image file", contentType));
        }
    }
}
