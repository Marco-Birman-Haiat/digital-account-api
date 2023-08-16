package com.myprojects.digital.account.exceptions;

public class DocumentInUseException extends RuntimeException{

    public DocumentInUseException(String message) {
        super(message);
    }
}
