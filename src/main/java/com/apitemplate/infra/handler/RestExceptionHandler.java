package com.apitemplate.infra.handler;

import com.apitemplate.dto.FieldErrorDTO;
import com.apitemplate.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    private ResponseEntity<RestErrorMessage> businessExceptionHandler(BusinessException exception) {
        log.info("Handle business rule exception: {}", exception.getMessage());
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<RestErrorMessage> runtimeExceptionHandler(RuntimeException exception) {
        log.info("Handle run time rule exception: {}", exception.getMessage());
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    private ResponseEntity<RestErrorMessage> usernameNotFoundExceptionHandler(UsernameNotFoundException exception) {
        log.info("Handle username not found exception: {}", exception.getMessage());
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<FieldErrorDTO>> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        log.info("Handle method argument not valid exception: {}", exception.getMessage());
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<FieldErrorDTO> fieldErrorDTOS = fieldErrors.stream().map(error -> new FieldErrorDTO(error.getField(), error.getDefaultMessage())).toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fieldErrorDTOS);
    }
}
