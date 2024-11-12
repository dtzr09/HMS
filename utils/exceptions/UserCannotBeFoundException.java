package utils.exceptions;

import model.user.enums.UserType;

/**
 * The {@link UserCannotBeFoundException} class is a custom exception that is
 * thrown when a specified user cannot be found in the system.
 */
public class UserCannotBeFoundException extends Exception {
    /**
     * Constructs a new UserCannotBeFoundException with a specific message
     * indicating the user type and staff ID that could not be found.
     *
     * @param staffId  the staff ID of the user that could not be found
     * @param userType the type of user (e.g., Doctor, Patient, Pharmacist,
     *                 Administrator)
     */
    public UserCannotBeFoundException(String staffId, UserType userType) {
        super(userType + " with staff id" + staffId + " cannot be found");
    }
}
