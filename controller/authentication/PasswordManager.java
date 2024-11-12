package controller.authentication;

import controller.user.UserManager;
import model.user.User;
import utils.exceptions.ModelNotFoundException;
import utils.exceptions.PasswordDoesNotFulfilCriteriaException;
import utils.exceptions.PasswordIncorrectException;

public class PasswordManager {
    /**
     * Check if the password is correct
     * 
     * @param user
     * @param password
     * @return true if the password is correct
     */
    public static boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    /**
     * Check if the password is valid
     * 
     * @param password
     * @return true if the password is valid
     * @throws PasswordDoesNotFulfilCriteriaException
     */
    private static boolean checkPasswordIsValid(String password) throws PasswordDoesNotFulfilCriteriaException {
        if (password.length() > 8) {
            return true;
        } else {
            throw new PasswordDoesNotFulfilCriteriaException();
        }
    }

    /**
     * Change the password of the user
     * 
     * @param user
     * @param oldPassword
     * @param newPassword
     * @throws PasswordIncorrectException
     * @throws PasswordDoesNotFulfilCriteriaException
     * @throws ModelNotFoundException
     */
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
