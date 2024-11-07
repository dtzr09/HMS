package display.auth;

import controller.account.AccountManager;
import display.ClearDisplay;
import display.WelcomeDisplay;
import display.user.AdministratorDisplay;
import display.user.DoctorDisplay;
import display.user.PharmacistDisplay;
import model.user.Administrator;
import model.user.Doctor;
import model.user.Pharmacist;
import model.user.User;
import model.user.enums.UserType;
import utils.exceptions.PageBackException;
import utils.exceptions.PasswordIncorrectException;
import utils.iocontrol.CustScanner;

public class LoginDisplay {
    public static void login(UserType userType) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("========================================");
        System.out.println("You are logging in as a " + userType.toString().toLowerCase());
        System.out.print("Enter your email address: ");
        String email = CustScanner.getStrChoice();

        // get user by email (check if user exists)
        while (email.isEmpty()) {
            System.out.println("Email cannot be empty.");
            System.out.print("Enter your email address: ");
            email = CustScanner.getStrChoice();
        }

        if (!AccountManager.userExist(email, userType)) {
            System.out.printf(
                    "Email does not exist. Press q to quit, r to retry, else any other key to register.");
            String choice = CustScanner.getStrChoice();
            if (choice.equalsIgnoreCase("q")) {
                System.exit(0);
            } else if (choice.equalsIgnoreCase("r")) {
                login(userType);
            }
            RegisterDisplay.registerDisplay();
        }

        // if user exists, check the password
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
                case ADMINISTRATOR ->
                    AdministratorDisplay.administratorDisplay((Administrator) user);
                case PHARMACIST -> PharmacistDisplay.pharmacistDisplay((Pharmacist) user);
                case DOCTOR -> DoctorDisplay.doctorDisplay((Doctor) user);

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
            WelcomeDisplay.welcome();
        } else {
            System.out.println("Please try again.");
            login(userType);
        }
    }
}
