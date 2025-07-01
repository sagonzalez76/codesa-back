package com.codesa.backend.exception;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String mensaje) {
        super(mensaje);
    }
}
