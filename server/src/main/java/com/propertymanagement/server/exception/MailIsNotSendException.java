package com.propertymanagement.server.exception;

public class MailIsNotSendException extends RuntimeException {
    public MailIsNotSendException(String message) {
        super(message);
    }
}
