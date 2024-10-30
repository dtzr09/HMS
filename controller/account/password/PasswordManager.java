package controller.account.password;

import model.user.User;
import utils.exceptions.PasswordIncorrectException;

public class PasswordManager {
    public static boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    public static void changePassword(User user, String oldPassword, String newPassword)
            throws PasswordIncorrectException {
        if (checkPassword(user, oldPassword)) {
            user.setPassword(newPassword);
        } else {
            throw new PasswordIncorrectException();
        }
    }
}
