package com.qaracter.novabank.exception;

public class TooManyWithdrawalsException extends RuntimeException {
    public TooManyWithdrawalsException(String message) {
        super(message);
    }
}
