package utils.exceptions;

/**
 * The {@link PasswordDoesNotFulfilCriteriaException} class is a custom
 * exception that is thrown when a password does not fulfill the required
 * criteria.
 */
public class PasswordDoesNotFulfilCriteriaException extends Exception {
    /**
     * Constructs a new PasswordDoesNotFulfilCriteriaException with a default
     * message.
     */
    public PasswordDoesNotFulfilCriteriaException() {
        super("Password has to be at least 8 characters long.");
    }

}
