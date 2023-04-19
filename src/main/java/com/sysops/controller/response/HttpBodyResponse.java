package com.sysops.controller.response;

public class HttpBodyResponse {
  private String message;

  public HttpBodyResponse(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}


