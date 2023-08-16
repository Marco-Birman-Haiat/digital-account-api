package com.myprojects.digital.account.exceptions;

public class DuplicatedTransferException extends  RuntimeException {
    public DuplicatedTransferException(String message) {
        super(message);
    }
}