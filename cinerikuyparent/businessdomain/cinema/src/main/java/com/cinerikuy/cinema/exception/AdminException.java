package com.cinerikuy.cinema.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class AdminException extends Exception {
    private String code;
    private HttpStatus httpStatus;

    public AdminException(String code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

}
