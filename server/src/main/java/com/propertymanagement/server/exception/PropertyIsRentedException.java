package com.propertymanagement.server.exception;

public class PropertyIsRentedException extends RuntimeException {
    public PropertyIsRentedException(String message) {
        super(message);
    }
}
