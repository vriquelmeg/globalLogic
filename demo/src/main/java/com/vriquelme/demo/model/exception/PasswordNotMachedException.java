package com.vriquelme.demo.model.exception;

public class PasswordNotMachedException extends RuntimeException{

    public PasswordNotMachedException (String message) {
        super(message);
    }
}
