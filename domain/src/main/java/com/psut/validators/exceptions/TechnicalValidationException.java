package com.psut.validators.exceptions;

public class TechnicalValidationException extends RuntimeException{
    public TechnicalValidationException(String errorMessage) {
        super(errorMessage);
    }
}
