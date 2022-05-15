package com.pmp.server.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage {
  private String message;
  private HttpStatus status;

  private Object data;

  public ResponseMessage(String message) {
    this.message = message;
  }

  public ResponseMessage(String message, HttpStatus status) {
    this.message = message;
    this.status = status;
  }


}
