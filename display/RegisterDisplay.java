package display;

import controller.account.AccountManager;
import model.user.UserType;
import utils.iocontrol.CustScanner;

public class RegisterDisplay {
    public static void register() {
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
        String userEmail = null;
        String name = null;
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
        } catch (Exception e) {
            System.out.println("Please try again.");
            register();
        }

        System.out.println();
        System.out.print("Enter your name: ");
        name = CustScanner.getStrChoice();
        while (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            System.out.print("Enter your Name: ");
            userEmail = CustScanner.getStrChoice();
        }

        System.out.println();
        System.out.print("Enter your email address: ");
        userEmail = CustScanner.getStrChoice();

        while (userEmail.isEmpty()) {
            System.out.println("Email cannot be empty.");
            System.out.print("Enter your email address: ");
            userEmail = CustScanner.getStrChoice();
        }

        try {
            AccountManager.register(userEmail, name, userType);
        } catch (Exception e) {
            System.out.println("Error registering user.");
            System.out.println("Enter [b] to go back to login page, else any other key to try again.");
            String choice = CustScanner.getStrChoice();
            if (choice.equalsIgnoreCase("b")) {
                LoginDisplay.loginDisplay();
            } else {
                System.out.println("Please try again.");
                register();
            }
        }
    }
}
