package com.pmp.server.exceptionHandler;

import com.pmp.server.dto.common.ResponseMessage;
import com.pmp.server.exceptionHandler.exceptions.UserNotFoundException;
import com.pmp.server.exceptionHandler.exceptions.CustomErrorException;
import com.stripe.exception.StripeException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import static com.pmp.server.utils.constants.ResponseMessageConstants.*;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
//  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getFieldErrors().forEach(error ->
      errors.put(error.getField(), error.getDefaultMessage()));

    return new ResponseEntity<>(new ResponseMessage(INVALID_REQUEST, HttpStatus.BAD_REQUEST, errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);


  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex) {
    Map<String, String> errors = new HashMap<>();

    ex.getConstraintViolations().forEach(cv -> {
      errors.put("message", cv.getMessage());
      errors.put("path", (cv.getPropertyPath()).toString());
    });

    return new ResponseEntity<>(new ResponseMessage(UNSUCCESSFUL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR, errors), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(UserNotFoundException.class)
  ResponseEntity<Object> handleException(UserNotFoundException ex) {
    return new ResponseEntity<>(new ResponseMessage(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null), new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(CustomErrorException.class)
  public ResponseEntity<?> handleCustomException(CustomErrorException ex) {
    ResponseMessage rm = new ResponseMessage();
    rm.setStatus(ex.getStatus());
    rm.setMessage(ex.getMessage());
    rm.setMessage((String) ex.getData());
    return new ResponseEntity<>(rm, new HttpHeaders(), ex.getStatus());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(StripeException.class)
  public ResponseEntity<?> handleStripeException(StripeException ex) {
    return new ResponseEntity<>(new ResponseMessage(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null), new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }
}

