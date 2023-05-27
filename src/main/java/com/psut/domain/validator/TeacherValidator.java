package com.psut.domain.validator;

import com.psut.model.teacher.Material;
import com.psut.model.teacher.Teacher;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class TeacherValidator {
    private final UsernameValidator usernameValidator;
    private final PasswordValidator passwordValidator;

    public Set<String> validate(Teacher teacher) {
        Set<String> violations = new ValidationHelper()
                .validateNotNull(teacher.getGender(), "gender")
                .validateNotBlank(teacher.getFirstName(), "first.name")
                .validateNotBlank(teacher.getLastName(), "last.name")
                .validateNotBlank(teacher.getCity(), "city")
                .validateNotBlank(teacher.getPhoneNumber(), "phone.number")
                .validateNonEmptyList(teacher.getTeachingWays(), "teaching.ways")
                .validateNonEmptyList(teacher.getTargetedStudents(), "targeted.students")
                .getViolations();

        validateEmail(teacher.getEmail(), violations);
        validateMaterials(teacher.getMaterials(), violations);
        usernameValidator.validate(teacher, violations);
        passwordValidator.validate(teacher.getPassword(), violations);

        return violations;
    }

    private void validateEmail(String email, Set<String> violations) {
        if (StringUtils.isBlank(email) || !email.contains("@")) {
            violations.add("invalid.email");
        }
    }

    private void validateMaterials(List<Material> materials, Set<String> violations) {
        if (!validMaterials(materials)) {
            violations.add("invalid.materials");
        }
    }

    private boolean validMaterials(List<Material> materials) {
        if (materials == null || materials.isEmpty()) {
            return false;
        }
        for (Material material : materials) {
            boolean isValidMaterial = !StringUtils.isBlank(material.getTitle())
                    && material.getStages() != null
                    && material.getCategory() != null
                    && !material.getStages().isEmpty()
                    && (material.getPricePerHour() != null || material.getPricePerCourse() != null);
            if (!isValidMaterial)
                return false;
        }
        return true;
    }
}
