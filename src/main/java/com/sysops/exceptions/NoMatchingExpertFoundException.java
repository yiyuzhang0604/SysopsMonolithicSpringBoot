package com.sysops.exceptions;

/**
 * This exception is thrown when there is no matching expert found in our system.
 */
public class NoMatchingExpertFoundException extends RuntimeException{
    public NoMatchingExpertFoundException(String message) {
        super(message);
    }
}
