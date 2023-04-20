package com.festus.login.exception;

import org.springframework.http.HttpStatusCode;

public class ApplicationException extends Exception{
    public ApplicationException() {
    }

    public ApplicationException(String message) {
        super(message);
    }
    public ApplicationException(String code, int statusCode, String message){
    }
}
