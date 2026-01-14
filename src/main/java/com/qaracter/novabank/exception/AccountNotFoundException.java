package com.qaracter.novabank.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
