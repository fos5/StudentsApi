package com.festus.login.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictRequestException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public ConflictRequestException(String message) {
        super(message);
    }
}
