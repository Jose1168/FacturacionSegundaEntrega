package com.coderhouse.java.middlewares;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

    private String message;
    private HttpStatus httpStatus;

    public ApiException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
