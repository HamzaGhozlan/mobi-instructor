package com.psut.exception;

public class TechnicalValidationException extends RuntimeException {
    public TechnicalValidationException(String errorMessage) {
        super(errorMessage);
    }
}
