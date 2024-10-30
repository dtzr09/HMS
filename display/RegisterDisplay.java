package display;

import controller.account.AccountManager;
import controller.account.password.PasswordManager;
import controller.account.user.UserManager;
import model.user.User;
import model.user.UserType;
import utils.iocontrol.CustScanner;

public class RegisterDisplay {

    private static void registerUserDisplay(UserType userType) {
        ClearDisplay.ClearConsole();
        System.out.println("========================================");
        System.out.println("HOSPITAL MANAGEMENT SYSTEM");
        System.out.println("========================================");
        System.out.println("You are registering as a " + userType);
        System.out.print("Enter your name: ");
        String name = CustScanner.getStrChoice();
        while (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            System.out.print("Enter your Name: ");
            name = CustScanner.getStrChoice();
        }

        System.out.print("Enter your email address: ");
        String userEmail = CustScanner.getStrChoice();

        while (userEmail.isEmpty()) {
            System.out.println("Email cannot be empty.");
            System.out.print("Enter your email address: ");
            userEmail = CustScanner.getStrChoice();
        }

        try {
            User user = AccountManager.register(userEmail, name, userType);
            if (user != null) {
                System.out.println("User registered successfully.");

                // Check if its the first time user logged in
                if (PasswordManager.checkPassword(user, "password")) {
                    System.out.print("Please enter a new password: ");
                    try {
                        PasswordManager.changePassword(user, "password", CustScanner.getPassword());
                    } catch (Exception e) {
                        System.out.println("Password change failed. Please try again.");
                        PasswordManager.changePassword(user, "password", CustScanner.getPassword());
                    }
                    UserManager.updateUser(user);
                    System.out.println("Password changed successfully.");
                }
                switch (userType) {
                    case ADMINISTRATOR -> AdministratorDisplay.AdministratorDisplay(user);
                    default -> throw new IllegalStateException("Unexpected value: " + userType);
                }
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println("Error registering user.");
            System.out.println("Enter [b] to go back to login page, else any other key to try again.");
            String choice = CustScanner.getStrChoice();
            if (choice.equalsIgnoreCase("b")) {
                registerDisplay();
            } else {
                System.out.println("Please try again.");
                registerUserDisplay(userType);
            }
        }
    }

    public static void registerDisplay() {
        ClearDisplay.ClearConsole();
        System.out.println("========================================");
        System.out.println("HOSPITAL MANAGEMENT SYSTEM");
        System.out.println("========================================");
        System.out.println("Register as a new user");
        System.out.println("1. Doctor");
        System.out.println("2. Patient");
        System.out.println("3. Pharmacist");
        System.out.println("4. Administrator");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");

        UserType userType = null;
        try {
            int choice = CustScanner.getIntChoice();
            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please try again.");
                throw new Exception();
            }
            userType = switch (choice) {
                case 1 -> UserType.DOCTOR;
                case 2 -> UserType.PATIENT;
                case 3 -> UserType.PHARMACIST;
                case 4 -> UserType.ADMINISTRATOR;
                default -> throw new IllegalStateException("Unexpected value: " + choice);
            };
            registerUserDisplay(userType);
        } catch (Exception e) {
            System.out.println("Please try again.");
            registerDisplay();
        }

    }
}
