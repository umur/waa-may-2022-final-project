package com.pmp.server.exception;

public class ErrorResourceException extends RuntimeException {
    public ErrorResourceException(String message){
        super(message);
    }
}
