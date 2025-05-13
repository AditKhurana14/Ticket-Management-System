package com.example.ticket.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<Object> buildResponse(String message, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Object> handleEmailAlreadyExists (EmailAlreadyExistsException ex){
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);



    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException (UserNotFoundException ex){
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);



    }
    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<Object> handleTicketNotFoundException (TicketNotFoundException ex){
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);



    }
    @ExceptionHandler(CommentNotFound.class)
    public ResponseEntity<Object> handleCommentNotFoundException (CommentNotFound ex){
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);



    }

    @ExceptionHandler(TagNotFoundExcepton.class)
    public ResponseEntity<Object> handleTagNotFoundException (TagNotFoundExcepton ex){
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);



    }
}
