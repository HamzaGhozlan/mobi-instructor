package com.psut.validators.exceptions;

import java.util.Set;

public class BusinessValidationException extends RuntimeException {
    public BusinessValidationException(String errorMessage) {
        super(errorMessage);
    }

    public BusinessValidationException(Set<String> violations) {
        super(getErrorMessage(violations));
    }

    private static String getErrorMessage(Set<String> violations) {
        return violations.stream()
                .reduce((m1, m2) -> m1 + ", " + m2)
                .orElse("");
    }
}
