package com.property.exception.custom;

public class ResourceNotFoundException extends RuntimeException{
    ResourceNotFoundException(String message){
        super(message);
    }
}
