package com.festus.login.error.exception;

public class ApplicationException extends Exception{
    public ApplicationException() {
    }

    public ApplicationException(String message) {
        super(message);
    }
    public ApplicationException( int statusCode, String message){
    }
}
