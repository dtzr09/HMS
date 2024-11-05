package display.auth;

import controller.account.AccountManager;
import display.ClearDisplay;
import model.user.User;
import model.user.enums.UserType;
import utils.exceptions.PageBackException;
import utils.iocontrol.CustScanner;

public class ChangePasswordDisplay {
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
            AccountManager.changePassword(userType, confirmPassword, oldPassword, newPassword);
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
