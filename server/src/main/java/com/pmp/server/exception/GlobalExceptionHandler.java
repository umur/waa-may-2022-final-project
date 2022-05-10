package com.pmp.server.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

    //400
    @ExceptionHandler(InvalidRequestException.class)
    ResponseEntity<Object> handleException(InvalidRequestException ex){
        HashMap<String,String> response = new HashMap<>();
        response.put("message",ex.getMessage());
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    //500
    @ExceptionHandler(ErrorResourceException.class)
    ResponseEntity<Object> handleException(ErrorResourceException ex){
        HashMap<String,String> response = new HashMap<>();
        response.put("message",ex.getMessage());
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}