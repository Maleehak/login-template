package com.backend.backend.RegisterAndLoginBackend.exception;

public class UserAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}