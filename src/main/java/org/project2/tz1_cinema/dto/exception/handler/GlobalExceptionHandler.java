package org.project2.tz1_cinema.dto.exception.handler;

import org.project2.tz1_cinema.dto.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for handling application-wide exceptions.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles UserNotFoundException and returns a 404 Not Found response.
     * @param ex The UserNotFoundException instance.
     * @return ResponseEntity with NOT_FOUND status and exception message.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles all other exceptions and returns a 500 Internal Server Error response.
     * @param ex The Exception instance.
     * @return ResponseEntity with INTERNAL_SERVER_ERROR status and error message.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Произошла ошибка: " + ex.getMessage());
    }
}
