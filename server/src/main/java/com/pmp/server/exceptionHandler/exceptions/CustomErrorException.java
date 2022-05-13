package com.pmp.server.exceptionHandler.exceptions;

import lombok.Data;
import lombok.Getter;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Data
public class CustomErrorException extends RuntimeException {
  private HttpStatus status = null;

  private Object data = null;

  public CustomErrorException() {
    super();
  }

  public CustomErrorException(
    String message
  ) {
    super(message);
  }

  public CustomErrorException(
    HttpStatus status,
    String message
  ) {
    this(message);
    this.status = status;
  }

  public CustomErrorException(
    HttpStatus status,
    String message,
    Object data
  ) {
    this(
      status,
      message
    );
    this.data = data;
  }
}
