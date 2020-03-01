package com.remitly.shortly.exception;

public class UrlNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Url not found!";
    }
}
