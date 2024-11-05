package utils.exceptions;

import model.user.enums.UserType;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(UserType userType, String email) {
        super(userType + "with " + email + " already exists");
    }

}
