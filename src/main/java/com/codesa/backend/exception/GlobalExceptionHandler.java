package com.codesa.backend.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;


import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Excepción personalizada: Recurso no encontrado
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex, WebRequest request) {
        Map<String, Object> error = new HashMap<>();
        error.put("mensaje", ex.getMessage());
        error.put("ruta", request.getDescription(false));
        error.put("estado", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        Map<String, Object> error = new HashMap<>();
        String message = ex.getRootCause() != null && ex.getRootCause().getMessage() != null
                ? ex.getRootCause().getMessage()
                : "Violación de integridad de datos.";

        if (message.contains("Duplicate entry") && message.contains("persona.email")) {
            message = "Ya existe una persona registrada con este correo electrónico.";
        }

        error.put("estado", HttpStatus.BAD_REQUEST.value());
        error.put("ruta", request.getDescription(false).replace("uri=", ""));
        error.put("mensaje", message);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Excepciones no controladas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobal(Exception ex, WebRequest request) {
        Map<String, Object> error = new HashMap<>();
        error.put("mensaje", "Error interno del servidor");
        error.put("estado", HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.put("ruta", request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, Object> error = new HashMap<>();

        FieldError firstError = ex.getBindingResult().getFieldErrors().stream().findFirst().orElse(null);

        String mensaje;
        if (firstError != null) {
            mensaje = firstError.getDefaultMessage();
        } else {
            mensaje = "Error de validación de datos.";
        }

        error.put("mensaje", mensaje);
        error.put("estado", HttpStatus.BAD_REQUEST.value());
        error.put("ruta", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        Map<String, Object> error = new HashMap<>();
        error.put("mensaje", ex.getMessage());
        error.put("ruta", request.getDescription(false).replace("uri=", ""));
        error.put("estado", HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }






}
