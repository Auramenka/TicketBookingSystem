package com.innowise.ticketbookingsystem.exceptions;

public class RollbackException extends RuntimeException {

    public RollbackException(String message) {
        super(message);
    }
}
