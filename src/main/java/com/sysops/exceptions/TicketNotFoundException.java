package com.sysops.exceptions;

/**
 * This exception is thrown when a ticket to retrieve is not found in our database.
 */
public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String message) {
        super(message);
    }
}
