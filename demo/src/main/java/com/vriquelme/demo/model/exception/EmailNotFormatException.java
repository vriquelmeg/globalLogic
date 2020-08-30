package com.vriquelme.demo.model.exception;

public class EmailNotFormatException extends RuntimeException{

    public EmailNotFormatException(String p_message) {
        super(p_message);
    }

}
