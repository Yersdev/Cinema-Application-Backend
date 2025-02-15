package org.project2.tz1_cinema.dto.exception;

/**
 * Custom exception thrown when a user is not found in the system.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     * @param message The detail message.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
