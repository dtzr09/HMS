package display;

import model.user.Administrator;
import model.user.User;
import utils.iocontrol.CustScanner;

public class AdministratorDisplay {

    public static void AdministratorDisplay(User user) {
        // ClearDisplay.ClearConsole();
        if (user instanceof Administrator administrator) {
            System.out.println("===================================");
            System.out.println("Welcome to Administrator Main Page");
            System.out.println("Hello, " + administrator.getName() + "!");
            System.out.println();
            System.out.println("\t1. View my profile");
            System.out.println("\t2. Change my password");
            System.out.println("\t10. Logout");
            System.out.println("===================================");
        }

        System.out.println();
        System.out.print("What would you like to do? ");
        int choice = CustScanner.getIntChoice();

    }

}
