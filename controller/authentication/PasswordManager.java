package controller.authentication;

import controller.user.UserManager;
import model.user.User;
import utils.exceptions.ModelNotFoundException;
import utils.exceptions.PasswordDoesNotFulfilCriteriaException;
import utils.exceptions.PasswordIncorrectException;

public class PasswordManager {
    /**
     * Checks if the provided password matches the user's stored password.
     * 
     * This method compares the provided password with the user's stored password to determine if they match.
     * 
     * @param user The user whose password is to be checked.
     * @param password The password to check against the user's stored password.
     * @return {@code true} if the provided password matches the stored password, {@code false} otherwise.
     */
    public static boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    /**
     * Validates if the provided password meets the required criteria (minimum length of 8 characters).
     * 
     * This method checks whether the given password satisfies the minimum length requirement of 8 characters.
     * If the password meets this requirement, it returns {@code true}; otherwise, it throws an exception.
     * 
     * @param password The password to be validated.
     * @return {@code true} if the password meets the criteria (length > 8), otherwise an exception is thrown.
     * @throws PasswordDoesNotFulfilCriteriaException if the password does not meet the length requirement.
     */
    private static boolean checkPasswordIsValid(String password) throws PasswordDoesNotFulfilCriteriaException {
        if (password.length() > 8) {
            return true;
        } else {
            throw new PasswordDoesNotFulfilCriteriaException();
        }
    }

    /**
     * Changes the password of a user after validating the old password and ensuring the new password meets the required criteria.
     * 
     * This method first checks if the provided old password matches the current password of the user. If it does not match, 
     * a {@link PasswordIncorrectException} is thrown. Then, it verifies if the new password satisfies the required criteria 
     * (e.g., minimum length) using the {@link #checkPasswordIsValid(String)} method. If the new password is valid, it updates 
     * the user's password and persists the changes through the {@link UserManager#updateUser(User)} method.
     * 
     * @param user The user whose password is to be changed.
     * @param oldPassword The current password of the user.
     * @param newPassword The new password to be set.
     * @throws PasswordIncorrectException if the provided old password does not match the current password.
     * @throws PasswordDoesNotFulfilCriteriaException if the new password does not meet the required criteria.
     * @throws ModelNotFoundException if the user model cannot be found while updating the user data.
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
