package com.nashss.se.mctracker.exceptions;

public class DateAfterTodayException extends RuntimeException{

    private static final long serialVersionUID = -279107187855770269L;

    public DateAfterTodayException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public DateAfterTodayException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public DateAfterTodayException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with a message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public DateAfterTodayException(String message, Throwable cause) {
        super(message, cause);
    }
}
