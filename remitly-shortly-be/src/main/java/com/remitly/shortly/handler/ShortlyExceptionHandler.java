package com.remitly.shortly.handler;

import com.remitly.shortly.exception.UrlNotFoundException;
import com.remitly.shortly.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ShortlyExceptionHandler {


    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<ErrorResponse> urlNotFoundExceptionHandler(UrlNotFoundException e) {
        return new ResponseEntity<>(createErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> runtimeExceptionHandler(MethodArgumentNotValidException e) {
        String defaultMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        return new ResponseEntity<>(createErrorResponse(defaultMessage, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }


    private ErrorResponse createErrorResponse(String message, HttpStatus httpStatus) {
       return new ErrorResponse((new Date()).toInstant().toEpochMilli(), httpStatus.value(), message);
    }

}
