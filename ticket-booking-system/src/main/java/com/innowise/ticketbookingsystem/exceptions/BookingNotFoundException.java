package com.innowise.ticketbookingsystem.exceptions;

public class BookingNotFoundException extends RuntimeException {

    public BookingNotFoundException(String message) {
        super(message);
    }
}
