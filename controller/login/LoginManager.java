package controller.login;

import display.ClearDisplay;
import model.user.UserType;
import utils.iocontrol.CustScanner;

public class LoginManager {
    public static void login(UserType userType) {
        ClearDisplay.ClearConsole();
        System.out.println("========================================");
        System.out.println("You are logging in as a " + userType.toString().toLowerCase());
        System.out.print("Enter your email address: ");
        String email = CustScanner.getStrChoice();
        System.out.println();

        // get user by email
        System.out.print("Enter your password: ");
        String password = CustScanner.getPassword();
    }
}
