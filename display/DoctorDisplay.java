package display;

import model.user.Doctor;
import model.user.User;
import utils.iocontrol.CustScanner;

public class DoctorDisplay {
    public static void DoctorDisplay(User user) {
        // ClearDisplay.ClearConsole();
        if (user instanceof Doctor doctor) {
            System.out.println("===================================");
            System.out.println("Welcome to Doctor Main Page");
            System.out.println("Hello, " + doctor.getName() + "!");
            System.out.println();
            System.out.println("\t1. View patient medical record");
            System.out.println("\t2. Update patient medical record");
            System.out.println("\t3. View calendar");
            System.out.println("\t4. Set availability for appointments");
            System.out.println("\t5. Accept or Decline Appointment Requests");
            System.out.println("\t6. View Upcoming Appointments");
            System.out.println("\t7. Record Appointment Outcome ");
            System.out.println("\t8. View my profile");
            System.out.println("\t9. Change my password");
            System.out.println("\t10. Logout");

            System.out.println("===================================");
        }

        int choice = 0;
        while (choice < 1 || choice > 10){
            System.out.println();
            System.out.print("What would you like to do? ");
            choice = CustScanner.getIntChoice();
        }
    }

}
