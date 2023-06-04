package com.cinerikuy.transaction.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

// TODO .. cambiar el nombre y usarlo
@Getter
@Setter
public class BusinessRuleException extends Exception {

    private String code;
    private HttpStatus httpStatus;

    public BusinessRuleException(String code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
