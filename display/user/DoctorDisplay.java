package display.user;

import controller.user.PatientManager;
import display.ClearDisplay;
import display.auth.ChangePasswordDisplay;
import display.auth.LogoutDisplay;
import model.user.Doctor;
import model.user.Patient;
import model.user.User;
import model.user.enums.UserType;
import utils.exceptions.PageBackException;
import utils.iocontrol.CustScanner;

public class DoctorDisplay {
    public static void doctorDisplay(User user) {
        ClearDisplay.ClearConsole();
        if (user instanceof Doctor doctor) {
            System.out.println("===================================");
            System.out.println("Welcome to Doctor Main Page");
            System.out.println("Hello, " + doctor.getName() + "!");
            System.out.println();
            System.out.println("\t1. View my patients");
            System.out.println("\t2. View patient medical record");
            System.out.println("\t3. Update patient medical record");
            System.out.println("\t4. View calendar");
            System.out.println("\t5. Set availability for appointments");
            System.out.println("\t6. Accept or Decline Appointment Requests");
            System.out.println("\t7. View Upcoming Appointments");
            System.out.println("\t8. Record Appointment Outcome ");
            System.out.println("\t9. View my profile");
            System.out.println("\t10. Change my password");
            System.out.println("\t11. Logout");

            System.out.println("===================================");

            System.out.println();
            System.out.print("What would you like to do? ");
            int choice = CustScanner.getIntChoice();

            try {
                switch (choice) {
                    case 1 -> displayMyPatients(doctor);
                    case 2 -> displayPatientsMenu(doctor, MedicalRecordsActions.VIEW);
                    case 3 -> displayPatientsMenu(doctor, MedicalRecordsActions.UPDATE);
                    case 9 -> ViewUserProfileDisplay.viewUserProfilePage(doctor, UserType.DOCTOR);
                    case 10 -> ChangePasswordDisplay.changePassword(doctor, UserType.DOCTOR);
                    case 11 -> LogoutDisplay.logout();
                    default -> {
                        System.out.println("Invalid choice. Please try again.");
                        doctorDisplay(user);
                    }
                }
            } catch (PageBackException e) {
                doctorDisplay(user);
            }
        } else {
            throw new IllegalArgumentException("User is not an doctor.");
        }

    }

    private static void displayMyPatients(Doctor doctor) throws PageBackException {
        ClearDisplay.ClearConsole();
        PatientDisplay.displayPatients(doctor);
        System.out.println();

        System.out.println("Press Enter to go back.");
        if (CustScanner.getStrChoice().equals(""))
            throw new PageBackException();
    }

    private static void displayPatientsMenu(Doctor doctor, MedicalRecordsActions actions) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("These are your current patients.");
        PatientDisplay.displayPatients(doctor);
        System.out.println("Please enter the ID of the patient you would like to " + actions.toString().toLowerCase()
                + " the medical record of.");
        String patientID = CustScanner.getStrChoice();

        Patient patient = PatientManager.getPatientById(patientID);

        if (patient == null) {
            System.out.println("Patient not found.");
            System.out.println("Press Enter to go back.");
            if (CustScanner.getStrChoice().equals(""))
                throw new PageBackException();
        }

        switch (actions) {
            case MedicalRecordsActions.VIEW -> viewPatientMedicalRecord(patient);
            case MedicalRecordsActions.UPDATE -> PatientDisplay.updatePatientMedicalRecord(patient, doctor);
            default -> {
                System.out.println("Invalid action.");
                throw new PageBackException();
            }
        }
    }

    private static void viewPatientMedicalRecord(Patient patient) throws PageBackException {
        ClearDisplay.ClearConsole();
        PatientDisplay.displayPatientInfo(patient);
        System.out.println();
        System.out.println("Press Enter to go back.");
        if (CustScanner.getStrChoice().equals(""))
            throw new PageBackException();
    }

}
