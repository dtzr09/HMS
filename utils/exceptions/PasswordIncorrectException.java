package utils.exceptions;

/**
 * The {@link PasswordIncorrectException} class is a custom exception that is
 * thrown
 * when an incorrect password is entered.
 */
public class PasswordIncorrectException extends Exception {
    /**
     * Constructs a new PasswordIncorrectException object with a default message.
     * The default message is "Password is incorrect".
     */
    public PasswordIncorrectException() {
        super("Password is incorrect");
    }
}
