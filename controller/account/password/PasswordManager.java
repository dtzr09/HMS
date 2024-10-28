package controller.account.password;

import model.user.User;
import utils.exceptions.PasswordIncorrectException;

public class PasswordManager {
    public static boolean checkPassword(User user, String password) {
        return user.getHashedPassword().equals(password);
    }

    public static void changePassword(User user, String oldPassword, String newPassword)
            throws PasswordIncorrectException {
        if (checkPassword(user, oldPassword)) {
            user.setHashedPassword(newPassword);
        } else {
            throw new PasswordIncorrectException();
        }
    }
}
