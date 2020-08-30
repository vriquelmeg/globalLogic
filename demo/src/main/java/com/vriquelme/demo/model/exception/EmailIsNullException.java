package com.vriquelme.demo.model.exception;

public class EmailIsNullException extends RuntimeException {
    public EmailIsNullException(String message) {
        super(message);
    }
}
