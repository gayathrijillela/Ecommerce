package com.eshop.sonny.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

  // Handle @Valid errors for @Valid annotations (e.g., in DTOs)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    List<String> errorMessages = new ArrayList<>();

    // Extract validation error messages and create response structure
    ex.getBindingResult().getAllErrors()
        .forEach(
            error -> errorMessages
                .add((error.getCodes() instanceof String[] ? error.getCodes()[1]
                    : "") + " " + error.getDefaultMessage()));

    Map<String, Object> response = Map.of("status", HttpStatus.BAD_REQUEST.value(), "message", errorMessages);

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  // Handle @Validated errors for method-level validations
  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Map<String, Object>> handleConstraintViolationException(ConstraintViolationException ex) {
    List<String> errorMessages = new ArrayList<>();

    // Extract validation error messages and create response structure
    ex.getConstraintViolations().forEach(violation -> errorMessages.add(violation.getMessage()));
    Map<String, Object> response = Map.of("status", HttpStatus.BAD_REQUEST.value(), "message", errorMessages);

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  // Handle data integrity violations
  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<Map<String, String>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
    Map<String, String> response = Map.of("message", "Data integrity violation: " + ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
