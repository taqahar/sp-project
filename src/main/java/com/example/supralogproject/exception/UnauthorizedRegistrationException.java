package com.example.supralogproject.exception;

public class UnauthorizedRegistrationException extends RuntimeException{

    public UnauthorizedRegistrationException(String message) {
        super(message);
    }
}
