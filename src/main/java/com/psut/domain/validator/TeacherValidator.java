package com.psut.domain.validator;

import com.psut.model.Material;
import com.psut.model.teacher.Teacher;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
public class TeacherValidator extends AbstractValidator {
    private final UsernameValidator usernameValidator;
    private final PasswordValidator passwordValidator;

    public Set<String> validate(Teacher teacher) {
        violations = new HashSet();

        validateNotNull(teacher.getGender(), "gender");
        validateNotBlank(teacher.getFirstName(), "first.name");
        validateNotBlank(teacher.getLastName(), "last.name");
        validateNotBlank(teacher.getCity(), "city");
        validateNotBlank(teacher.getPhoneNumber(), "phone.number");
        validateNonEmptyList(teacher.getTeachingWay(), "teaching.ways");
        validateNonEmptyList(teacher.getTargetedStudents(), "targeted.students");

        validateEmail(teacher.getEmail());
        validateMaterials(teacher.getMaterials());

        usernameValidator.validate(teacher, violations);
        passwordValidator.validate(teacher.getPassword(), violations);

        return violations;
    }

    private void validateEmail(String email) {
        if (StringUtils.isBlank(email) || !email.contains("@")) {
            violations.add("invalid.email");
        }
    }

    private void validateMaterials(List<Material> materials) {
        if (!validMaterials(materials)) {
            violations.add("invalid.materials");
        }
    }

    private boolean validMaterials(List<Material> materials) {
        if (Objects.isNull(materials) || materials.isEmpty()) {
            return false;
        }
        for (Material material : materials) {
            boolean isValidMaterial = !StringUtils.isBlank(material.getTitle())
                    && !material.getStages().isEmpty()
                    && Objects.nonNull(material.getStages())
                    && Objects.nonNull(material.getCategory())
                    && (Objects.nonNull(material.getPricePerHour()) || Objects.nonNull(material.getPricePerCourse()));
            if (!isValidMaterial)
                return false;
        }
        return true;
    }
}
