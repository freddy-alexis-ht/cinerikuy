package com.cinerikuy.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.UnknownHostException;

/**
 * Standard HTTP communication has five levels of response codes
 * 100-level (Informational) — Server acknowledges a request, it means that request was received and understood, it is transient response , alert client for awaiting response
 * 200-level (Success) — Server completed the request as expected
 * 300-level (Redirection) — Client needs to perform further actions to complete the request
 * 400-level (Client error) — Client sent an invalid request
 * 500-level (Server error) — Server failed to fulfill a valid request due to an error with server
 */

@RestControllerAdvice // This class assists a controller class and can have a body in response
public class ApiExceptionHandler {

//    // Generic method as example
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<StandardizedApiExceptionResponse> handleGenericException(Exception ex) {
//        StandardizedApiExceptionResponse response = new StandardizedApiExceptionResponse("Generic-title", "Generic-code", ex.getMessage());
//        return new ResponseEntity(response, HttpStatus.PARTIAL_CONTENT);
//    }

    // La clase BusinessRuleException ya trae el 'code', se toma de ahí
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<StandardizedApiExceptionResponse> handleBusinessRuleException(BusinessRuleException ex) {
        StandardizedApiExceptionResponse response = new StandardizedApiExceptionResponse("Error de validación",ex.getCode(),ex.getMessage());
        return new ResponseEntity(response, ex.getHttpStatus());
    }
}
