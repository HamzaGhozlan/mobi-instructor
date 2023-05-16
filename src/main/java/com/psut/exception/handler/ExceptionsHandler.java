package com.psut.exception.handler;

import com.psut.exception.BusinessValidationException;
import com.psut.exception.model.ExceptionInfo;
import com.psut.exception.RecordNotFoundException;
import com.psut.exception.TechnicalValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;


@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException ex) {
        return handleException(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TechnicalValidationException.class)
    public ResponseEntity<Object> handleTechnicalValidationException(TechnicalValidationException ex) {
        return handleException(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BusinessValidationException.class)
    public ResponseEntity<Object> handleBusinessValidationException(BusinessValidationException ex) {
        return handleException(ex, HttpStatus.BAD_REQUEST);
    }

    private static ResponseEntity<Object> handleException(RuntimeException ex, HttpStatus httpStatus) {
        ExceptionInfo exceptionInfo = new ExceptionInfo(
                Optional.ofNullable(ex.getMessage()).orElse("error"),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exceptionInfo, httpStatus);
    }
}
