package com.sysops.exceptions;

/**
 * This exception is thrown when a customer to retrieve is not found in our database.
 */
public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
