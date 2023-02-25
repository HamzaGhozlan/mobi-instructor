package com.psut.validators;

import io.micrometer.common.util.StringUtils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractValidator {
    protected Set<String> violations = new HashSet();

    protected void validateNotNull(Object object, String field) {
        if(Objects.isNull(object)) {
            violations.add("invalid." + field);
        }
    }

    protected void validateNotBlank(String string, String field) {
        if(StringUtils.isBlank(string)) {
            violations.add("invalid." + field);
        }
    }
}
