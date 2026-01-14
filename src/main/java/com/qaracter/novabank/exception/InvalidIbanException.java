package com.qaracter.novabank.exception;

public class InvalidIbanException extends RuntimeException {
    public InvalidIbanException(String message) {
        super(message);
    }
}
