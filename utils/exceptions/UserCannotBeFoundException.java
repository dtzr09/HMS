package utils.exceptions;

import model.user.enums.UserType;

public class UserCannotBeFoundException extends Exception {
    public UserCannotBeFoundException(String staffId, UserType userType) {
        super(userType + " with staff id" + staffId + " cannot be found");
    }
}
