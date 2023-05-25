package com.psut.domain.validator;

import io.micrometer.common.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidationHelper {
    private final Set<String> violations = new HashSet<>();

    public ValidationHelper validateNotNull(Object object, String fieldName) {
        if (object == null) {
            violations.add("invalid." + fieldName);
        }
        return this;
    }

    public ValidationHelper validateNotBlank(String value, String fieldName) {
        if (StringUtils.isBlank(value)) {
            violations.add("invalid." + fieldName);
        }
        return this;
    }

    public ValidationHelper validateNonEmptyList(List<?> list, String fieldName) {
        if (list == null || list.isEmpty()) {
            violations.add("invalid." + fieldName);
        }
        return this;
    }

    public Set<String> getViolations() {
        return violations;
    }
}
