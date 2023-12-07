package com.nashss.se.mctracker.exceptions;

public class UnauthorizedUserException extends RuntimeException {

    private static final long serialVersionUID = -3770212442392788818L;

    /**
     * Exception with no message or cause.
     */
    public UnauthorizedUserException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public UnauthorizedUserException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public UnauthorizedUserException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with a message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public UnauthorizedUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
