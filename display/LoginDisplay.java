package display;

import controller.login.LoginManager;
import model.user.UserType;
import utils.iocontrol.CustScanner;

public class LoginDisplay {
    public static void loginDisplay() {
        ClearDisplay.ClearConsole();
        System.out.println("========================================");
        System.out.println("HOSPITAL MANAGEMENT SYSTEM");

        // Check if user is new
        System.out.print("\tAre you a new user? [y/n]");
        if (CustScanner.getStrChoice().equalsIgnoreCase("y")) {
            RegisterDisplay.register();
        }

        System.out.println("Who are you logging in as?");
        System.out.println("1. Doctor");
        System.out.println("2. Patient");
        System.out.println("3. Pharmacist");
        System.out.println("4. Administrator");
        System.out.println("5. Exit");

        System.out.print("Enter your choice: ");
        try {
            int choice = CustScanner.getIntChoice();
            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please try again.");
                throw new Exception();
            }
            UserType userType = switch (choice) {
                case 1 -> UserType.DOCTOR;
                case 2 -> UserType.PATIENT;
                case 3 -> UserType.PHARMACIST;
                case 4 -> UserType.ADMINISTRATOR;
                default -> throw new IllegalStateException("Unexpected value: " + choice);
            };
            LoginManager.login(userType);

        } catch (Exception e) {
            loginDisplay();
        }
    }
}
