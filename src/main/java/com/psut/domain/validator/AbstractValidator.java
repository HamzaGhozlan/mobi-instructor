package com.psut.domain.validator;

import io.micrometer.common.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractValidator {
    protected Set<String> violations;

    protected void validateNotNull(Object object, String fieldName) {
        if (Objects.isNull(object)) {
            violations.add("invalid." + fieldName);
        }
    }

    protected void validateNotBlank(String value, String fieldName) {
        if (StringUtils.isBlank(value)) {
            violations.add("invalid." + fieldName);
        }
    }

    protected void validateNonEmptyList(List<?> list, String fieldName) {
        if(Objects.isNull(list) || list.isEmpty()) {
            violations.add("invalid." + fieldName);
        }
    }
}
