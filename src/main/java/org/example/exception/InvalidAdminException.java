package org.example.exception;

public class InvalidAdminException extends RuntimeException{
    public  InvalidAdminException(String message){
        super(message);
    }
}
