package com.myprojects.digital.account.exceptions;

public class InsufficientValueException extends RuntimeException {
    public InsufficientValueException(String message) {
        super(message);
    }

}
