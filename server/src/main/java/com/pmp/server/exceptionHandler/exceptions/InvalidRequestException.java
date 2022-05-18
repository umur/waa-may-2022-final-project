package com.pmp.server.exceptionHandler.exceptions;


import org.springframework.validation.Errors;

@SuppressWarnings("serial")
public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(String msg) {
        super(msg);
    }
}