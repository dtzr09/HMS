package display;

import model.user.Patient;
import model.user.User;
import utils.iocontrol.CustScanner;

public class PatientDisplay {
    public static void PatientDisplay(User user) {
        // ClearDisplay.ClearConsole();
        if (user instanceof Patient patient) {
            System.out.println("===================================");
            System.out.println("Welcome to Patient Main Page");
            System.out.println("Hello, " + patient.getName() + "!");
            System.out.println();
            System.out.println("\t1. View medical record");
            System.out.println("\t2. Update personal information");
            System.out.println("\t3. View available appointment slots");
            System.out.println("\t4. Schedule an appointment");
            System.out.println("\t5. Reschedule an appointment");
            System.out.println("\t6. Cancel an appointment");
            System.out.println("\t7. View scheduled appointments");
            System.out.println("\t8. View Past Appointment Outcome Records");
            System.out.println("\t9. View my profile");
            System.out.println("\t10. Change my password");
            System.out.println("\t11. Logout");

            System.out.println("===================================");
        }

        int choice = 0;
        while (choice < 1 || choice > 12){
            System.out.println();
            System.out.print("What would you like to do? ");
            choice = CustScanner.getIntChoice();
        }

    }

}
