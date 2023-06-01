package com.cinerikuy.movie.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class MovieDetailsException extends Exception {
    private String code;
    private HttpStatus httpStatus;

    public MovieDetailsException(String code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

}
