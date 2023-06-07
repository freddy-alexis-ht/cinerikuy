package com.cinerikuy.cinema.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Standard HTTP communication has five levels of response codes
 * 100-level (Informational) — Server acknowledges a request, it means that request was received and understood, it is transient response , alert client for awaiting response
 * 200-level (Success) — Server completed the request as expected
 * 300-level (Redirection) — Client needs to perform further actions to complete the request
 * 400-level (Client error) — Client sent an invalid request
 * 500-level (Server error) — Server failed to fulfill a valid request due to an error with server
 */

@RestControllerAdvice
public class ApiExceptionHandler {

    // For generic method use: @ExceptionHandler(Exception.class)
    @ExceptionHandler(CinemaListException.class)
    public ResponseEntity<ApiExceptionResponse> handleCinemaListException(CinemaListException ex) {
        ApiExceptionResponse response = new ApiExceptionResponse("LIST VALIDATION", ex.getCode(), ex.getMessage(), ex.getHttpStatus().toString());
        return new ResponseEntity(response, ex.getHttpStatus());
    }

    @ExceptionHandler(AdminException.class)
    public ResponseEntity<ApiExceptionResponse> handleAdminException(AdminException ex) {
        ApiExceptionResponse response = new ApiExceptionResponse("[ADMIN] CITY VALIDATION", ex.getCode(), ex.getMessage(), ex.getHttpStatus().toString());
        return new ResponseEntity(response, ex.getHttpStatus());
    }



}
