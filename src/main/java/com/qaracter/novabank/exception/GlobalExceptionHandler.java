package com.qaracter.novabank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleAccountNotFound(AccountNotFoundException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", exception.getMessage());
        response.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidIbanException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidIban(InvalidIbanException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", exception.getMessage());
        response.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidAmount(InvalidAmountException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", exception.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<Map<String, Object>> handleInsufficientBalance(InsufficientBalanceException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", exception.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TooManyWithdrawalsException.class)
    public ResponseEntity<Map<String, Object>> handleTooManyWithdrawals(TooManyWithdrawalsException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", exception.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.TOO_MANY_REQUESTS);
    }
}