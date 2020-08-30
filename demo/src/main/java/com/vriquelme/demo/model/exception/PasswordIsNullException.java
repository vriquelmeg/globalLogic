package com.vriquelme.demo.model.exception;

public class PasswordIsNullException extends RuntimeException {

    public PasswordIsNullException(String message) {
        super(message);
    }

}
