package controller.authentication;

import controller.user.UserManager;
import model.user.User;
import utils.exceptions.ModelNotFoundException;
import utils.exceptions.PasswordDoesNotFulfilCriteriaException;
import utils.exceptions.PasswordIncorrectException;

public class PasswordManager {
    public static boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    private static boolean checkPasswordIsValid(String password) throws PasswordDoesNotFulfilCriteriaException {
        if (password.length() > 8) {
            return true;
        } else {
            throw new PasswordDoesNotFulfilCriteriaException();
        }
    }

    public static void changePassword(User user, String oldPassword, String newPassword)
            throws PasswordIncorrectException, PasswordDoesNotFulfilCriteriaException, ModelNotFoundException {

        if (!checkPassword(user, oldPassword)) {
            throw new PasswordIncorrectException();
        }

        if (!checkPasswordIsValid(newPassword)) {
            throw new PasswordDoesNotFulfilCriteriaException();
        }

        user.setPassword(newPassword);
        UserManager.updateUser(user);
    }
}
