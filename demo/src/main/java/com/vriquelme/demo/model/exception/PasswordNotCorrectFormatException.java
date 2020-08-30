package com.vriquelme.demo.model.exception;

public class PasswordNotCorrectFormatException extends RuntimeException {

    public PasswordNotCorrectFormatException(String message) {
        super(message);
    }
}
