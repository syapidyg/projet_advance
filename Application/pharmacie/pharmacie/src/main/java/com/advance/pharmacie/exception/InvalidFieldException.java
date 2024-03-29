package com.advance.pharmacie.exception;

public class InvalidFieldException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String message;

    public InvalidFieldException(String message) {
        this.setMessage(message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}