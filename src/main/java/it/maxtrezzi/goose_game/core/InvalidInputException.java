package it.maxtrezzi.goose_game.core;

/**
 * Exception for errors caused by user inputs
 * @author maxtrezzi
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
