package display.user;

import display.ClearDisplay;
import model.user.Pharmacist;
import model.user.User;
import utils.iocontrol.CustScanner;

public class PharmacistDisplay {
    public static void display(User user) {
        ClearDisplay.ClearConsole();
        if (user instanceof Pharmacist pharmacist) {
            System.out.println("===================================");
            System.out.println("Welcome to Pharmacist Main Page");
            System.out.println("Hello, " + pharmacist.getName() + "!");
            System.out.println();
            System.out.println("\t1. View appointment outcome record");
            System.out.println("\t2. Update prescription status");
            System.out.println("\t3. View medication inventory");
            System.out.println("\t4. Submit medication replenishment request");
            System.out.println("\t5. View my profile");
            System.out.println("\t6. Change my password");
            System.out.println("\t7. Logout");
            System.out.println("===================================");
        }

        int choice = 0;
        while (choice < 1 || choice > 7) {
            System.out.println();
            System.out.print("What would you like to do? ");
            choice = CustScanner.getIntChoice();
        }
    }

}
