package display;

import display.auth.LoginDisplay;
import display.auth.RegisterDisplay;
import model.user.enums.UserType;
import utils.iocontrol.CustScanner;

public class WelcomeDisplay {
    /**
     * Displays the welcome message and prompts the user to register or login.
     */
    public static void welcome() {
        ClearDisplay.ClearConsole();
        System.out.println("========================================");
        System.out.println("HOSPITAL MANAGEMENT SYSTEM");
        System.out.println("========================================");
        System.out.println();

        // Check if user is new
        System.out.print("Are you a new user? [y/n], or 'q' to quit: ");
        String strChoice = CustScanner.getStrChoice();
        if (strChoice.equalsIgnoreCase("y")) {
            RegisterDisplay.registerDisplay();
        } else if (strChoice.equalsIgnoreCase("q")) {
            System.exit(0);
        } else if (strChoice.equalsIgnoreCase("n")) {
            System.out.println();
            System.out.println("Who are you logging in as?");
            System.out.println("1. Doctor");
            System.out.println("2. Patient");
            System.out.println("3. Pharmacist");
            System.out.println("4. Administrator");
            System.out.println("5. Exit");
            System.out.println();
            System.out.print("Enter your choice: ");
            try {
                int choice = CustScanner.getIntChoice();
                if (choice < 1 || choice > 5) {
                    System.out.println("Invalid choice. Please try again.");
                    throw new Exception();
                }
                if (choice == 5) {
                    System.exit(0);
                }
                UserType userType = switch (choice) {
                    case 1 -> UserType.DOCTOR;
                    case 2 -> UserType.PATIENT;
                    case 3 -> UserType.PHARMACIST;
                    case 4 -> UserType.ADMINISTRATOR;
                    default -> throw new IllegalStateException("Unexpected value: " + choice);
                };
                LoginDisplay.login(userType);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Invalid choice. Please try again.");
                welcome();
            }
        } else {
            System.out.println("Invalid choice. Please try again.");
            welcome();
        }

    }
}
