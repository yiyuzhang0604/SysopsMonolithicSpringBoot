package com.sysops.exceptions;

/**
 * This exception is thrown when an expert to retrieve is not found in our database.
 */
public class ExpertNotFoundException extends RuntimeException {
    public ExpertNotFoundException(String message) {
        super(message);
    }
}
