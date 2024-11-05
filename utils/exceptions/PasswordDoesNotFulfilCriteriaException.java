package utils.exceptions;

public class PasswordDoesNotFulfilCriteriaException extends Exception {
    public PasswordDoesNotFulfilCriteriaException() {
        super("Password has to be at least 8 characters long.");
    }

}
