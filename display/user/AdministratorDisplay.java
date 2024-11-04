package display.user;

import display.ClearDisplay;
import model.user.Administrator;
import model.user.User;
import utils.iocontrol.CustScanner;

public class AdministratorDisplay {

    public static void display(User user) {
        ClearDisplay.ClearConsole();
        if (user instanceof Administrator administrator) {
            System.out.println("===================================");
            System.out.println("Welcome to Administrator Main Page");
            System.out.println("Hello, " + administrator.getName() + "!");
            System.out.println();
            System.out.println("\t1. View hospital staffs");
            System.out.println("\t2. Manage hospital staffs");
            System.out.println("\t3. View appointments details");
            System.out.println("\t4. View medication inventory");
            System.out.println("\t5. Manage medication inventory");
            System.out.println("\t6. Approve medication replenishment request");
            System.out.println("\t7. View my profile");
            System.out.println("\t8. Change my password");
            System.out.println("\t9. Logout");
            System.out.println("===================================");
        }

        System.out.println();
        System.out.print("What would you like to do? ");
        int choice = CustScanner.getIntChoice();

    }

}
