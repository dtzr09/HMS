package display.user;

import controller.medication.PrescriptionManager;
import model.user.User;
import model.user.enums.UserType;
import display.ClearDisplay;
import display.auth.ChangePasswordDisplay;
import display.auth.LogoutDisplay;
import model.prescription.PrescriptionStatus;
import model.user.Patient;
import model.user.Pharmacist;
import utils.exceptions.ModelNotFoundException;
import utils.exceptions.PageBackException;
import utils.iocontrol.CustScanner;

import database.user.PatientDatabase;

public class PharmacistDisplay {

    public static void pharmacistDisplay(User user) {
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
            System.out.println();
            System.out.print("What would you like to do? ");
            int choice = CustScanner.getIntChoice();

            try {
                switch (choice) {
                    // case 1 -> ; ////ToDo Add appointment parts and implement function here
                    case 2 -> updatePrescriptionStatus(user);
                    // case 3 -> ;
                    // case 4 -> ;
                    case 5 -> ViewUserProfileDisplay.viewUserProfilePage(pharmacist, UserType.PHARMACIST);
                    case 6 -> ChangePasswordDisplay.changePassword(pharmacist, UserType.PHARMACIST);
                    case 7 -> LogoutDisplay.logout();
                    default -> {
                        System.out.println("Invalid choice. Please try again.");
                        pharmacistDisplay(user);
                    }
                }
            } catch (PageBackException e) {
                pharmacistDisplay(user);
            }
        } else {
            throw new IllegalArgumentException("User is not a Pharmacist.");
        }

    }

    public static void updatePrescriptionStatus(User user) {
        try {
            System.out.println("Enter Patient's email : ");
            String email = CustScanner.getStrChoice();
            Patient patient = PatientDatabase.getDB().getByEmail(email);
            patient.printAllDiagnosis();
            System.out.println("Choose the diagnosis ID ");
            String diagnosisID = CustScanner.getStrChoice();
            System.out.println("Choose Prescription ID to change status ");
            PrescriptionManager.printAllPendingPrescriptioinsIDs(patient.getOneDiagnosis(diagnosisID));
            String prescriptionID = CustScanner.getStrChoice();
            System.out.println("Choose the options below to change the status");
            System.out.println("1. PENDING");
            System.out.println("2. DISPENSED");
            System.out.println("3. DECLINED");
            int i = CustScanner.getIntChoice();
            switch (i) {
                case 1:
                    PrescriptionManager.updatePrescriptionStatus(patient.getOneDiagnosis(diagnosisID), prescriptionID,
                            PrescriptionStatus.PENDING);
                    break;
                case 2:
                    PrescriptionManager.updatePrescriptionStatus(patient.getOneDiagnosis(diagnosisID), prescriptionID,
                            PrescriptionStatus.DISPENSED);
                    break;
                case 3:
                    PrescriptionManager.updatePrescriptionStatus(patient.getOneDiagnosis(diagnosisID), prescriptionID,
                            PrescriptionStatus.DECLINED);
                    break;
                default:
                    System.out.println("INVALID CHOICE");
            }

        } catch (ModelNotFoundException e) {
            pharmacistDisplay(user);
        }

    }

}
