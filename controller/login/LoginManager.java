package controller.login;

import controller.account.AccountManager;
import controller.account.password.PasswordManager;
import display.AdministratorDisplay;
import display.ClearDisplay;
import display.LoginDisplay;
import display.RegisterDisplay;
import model.user.User;
import model.user.UserType;
import utils.exceptions.PasswordIncorrectException;
import utils.iocontrol.CustScanner;

public class LoginManager {
    public static void login(UserType userType) {
        ClearDisplay.ClearConsole();
        System.out.println("========================================");
        System.out.println("You are logging in as a " + userType.toString().toLowerCase());
        System.out.print("Enter your email address: ");
        String email = CustScanner.getStrChoice(); // TODO; can add in email manager to check if email is valid

        // get user by email (check if user exists)
        while (email.isEmpty()) {
            System.out.println("Email cannot be empty.");
            System.out.print("Enter your email address: ");
            email = CustScanner.getStrChoice();
        }

        // if user does not exist, ask to register
        if (!AccountManager.userExist(email, userType)) {
            RegisterDisplay.registerDisplay();
        }

        // if user exists, check the password
        System.out.println();
        System.out.print("Enter your password: ");
        String password = CustScanner.getPassword();

        while (password.isEmpty()) {
            System.out.println("password cannot be empty.");
            System.out.print("Enter your password: ");
            password = CustScanner.getPassword();
        }

        try {
            User user = AccountManager.login(userType, email, password);

            switch (userType) {
                case ADMINISTRATOR -> AdministratorDisplay.AdministratorDisplay(user);
                // case DOCTOR:
                // // DoctorDisplay.DoctorDisplay(user);
                // break;
                // case PATIENT:
                // // PatientDisplay.PatientDisplay(user);
                // break;
                // case PHARMACIST:
                // // PharmacistDisplay.PharmacistDisplay(user);
                // break;
                default -> throw new IllegalStateException("Unexpected value: " + userType);
            }
        } catch (PasswordIncorrectException e) {
            System.out.println("Password incorrect.");
        } catch (Exception e) {
            System.out.println("Invalid email or password. Please try again.");
            login(userType);
        }

        System.out.println("Enter [b] to go back, else any other key to try again.");
        String choice = CustScanner.getStrChoice();
        if (choice.equalsIgnoreCase("b")) {
            LoginDisplay.loginDisplay();
        } else {
            System.out.println("Please try again.");
            login(userType);
        }
    }
}
