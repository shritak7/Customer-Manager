package com.example.SunBaseAssignment.customExceptions;

public class InvalidSearchException extends RuntimeException{
    public InvalidSearchException(String message) {
        super(message);
    }
}
