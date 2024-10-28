package utils.exceptions;

public class PasswordIncorrectException extends Exception {
    /**
     * Constructs a new PasswordIncorrectException object with a default message.
     * The default message is "Password is incorrect".
     */
    public PasswordIncorrectException() {
        super("Password is incorrect");
    }
}
