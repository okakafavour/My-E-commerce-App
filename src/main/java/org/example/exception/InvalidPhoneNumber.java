package org.example.exception;

public class InvalidPhoneNumber extends RuntimeException{
    public InvalidPhoneNumber(String message){
        super(message);
    }
}
