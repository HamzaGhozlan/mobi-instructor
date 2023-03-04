package com.psut.exception;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException() {
        super();
    }

    public RecordNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
