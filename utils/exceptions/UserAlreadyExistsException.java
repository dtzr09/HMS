package utils.exceptions;

import model.user.enums.UserType;

/**
 * The {@link UserAlreadyExistsException} class is a custom
 * exception that is thrown when attempting to create a user that already
 * exists.
 */
public class UserAlreadyExistsException extends Exception {
    /**
     * Constructs a new UserAlreadyExistsException with a specific message
     * indicating
     * the user type and email that already exist.
     *
     * @param userType the type of user (e.g., Doctor, Patient, Pharmacist,
     *                 Administrator)
     * @param email    the email address of the user that already exists
     */
    public UserAlreadyExistsException(UserType userType, String email) {
        super(userType + "with " + email + " already exists");
    }

}
