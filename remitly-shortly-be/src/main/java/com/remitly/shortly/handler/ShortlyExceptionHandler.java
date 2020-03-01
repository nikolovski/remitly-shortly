package com.remitly.shortly.handler;

import com.remitly.shortly.exception.UrlNotFoundException;
import com.remitly.shortly.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ShortlyExceptionHandler {


    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<ErrorResponse> urlNotFoundExceptionHandler(UrlNotFoundException e) {
        return new ResponseEntity<>(createErrorResponse(e, HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    private ErrorResponse createErrorResponse(Exception e, HttpStatus httpStatus) {
       return new ErrorResponse((new Date()).toInstant().toEpochMilli(), httpStatus.value(), e.getMessage());
    }

}
