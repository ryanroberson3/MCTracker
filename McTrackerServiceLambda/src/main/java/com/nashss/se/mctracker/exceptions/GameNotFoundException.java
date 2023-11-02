package com.nashss.se.mctracker.exceptions;

public class GameNotFoundException extends RuntimeException{


    private static final long serialVersionUID = 5945713333176633221L;

    public GameNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public GameNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public GameNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with a message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public GameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
