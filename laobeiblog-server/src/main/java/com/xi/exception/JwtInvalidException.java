package com.xi.exception;

public class JwtInvalidException extends Exception{
    private String message;

    public JwtInvalidException(String message) {
        super(message);
        this.message = message;
    }
}
