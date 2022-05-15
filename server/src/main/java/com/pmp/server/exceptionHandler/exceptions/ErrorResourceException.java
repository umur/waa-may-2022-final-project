package com.pmp.server.exceptionHandler.exceptions;

public class ErrorResourceException extends RuntimeException {
    public ErrorResourceException(String message){
        super(message);
    }
}
