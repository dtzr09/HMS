package display.auth;

import controller.account.AccountManager;
import display.ClearDisplay;
import model.user.User;
import model.user.enums.UserType;
import utils.exceptions.PageBackException;
import utils.iocontrol.CustScanner;

/**
 * The {@code ChangePasswordDisplay} class provides a user interface for
 * changing
 * a user's password. It prompts the user to enter their old password, new
 * password,
 * and confirmation of the new password, and handles validation checks such as
 * matching
 * passwords and handling invalid input.
 */
public class ChangePasswordDisplay {

    /**
     * Displays the password change interface for the specified user. This method
     * prompts the user to enter their old password and a new password twice for
     * confirmation. It performs validation checks to ensure the new password
     * entries
     * match. If the passwords do not match or an error occurs during the password
     * change, the user can retry or exit the process.
     * 
     * @param user     the {@code User} object representing the user attempting to
     *                 change their password
     * @param userType the {@code UserType} representing the type of user (e.g.,
     *                 Admin, Patient, etc.)
     * @throws PageBackException if the user decides to exit the password change
     *                           process
     */
    public static void changePassword(User user, UserType userType) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("=====================================");
        System.out.println("Change Your Password");
        System.out.println("=====================================");
        System.out.println("Enter your old password: ");
        String oldPassword = CustScanner.getPassword();

        System.out.println("Enter your new password: ");
        String newPassword = CustScanner.getPassword();
        System.out.println("Confirm your new password: ");
        String confirmPassword = CustScanner.getPassword();

        if (!newPassword.equals(confirmPassword)) {
            System.out.println("Passwords do not match. Please try again or type 'exit' to exit.");
            String input = CustScanner.getStrChoice();
            if (input.equals("exit")) {
                throw new PageBackException();
            }
            changePassword(user, userType);
        }

        try {
            AccountManager.changePassword(userType, user.getModelEmail(), oldPassword, newPassword);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again or type 'exit' to exit.");
            String input = CustScanner.getStrChoice();
            if (input.equals("exit")) {
                throw new PageBackException();
            }
            changePassword(user, userType);
        }
    }
}
