package com.example.ticket.management.exception;

public class EmailAlreadyExistsException extends RuntimeException {
  public EmailAlreadyExistsException(String message) {
    super(message);
  }
}
