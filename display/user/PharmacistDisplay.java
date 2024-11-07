package display.user;

import controller.user.PharmacistManager;
import controller.user.UserManager;
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
            System.out.println("\t4. View low stock medication inventory");
            System.out.println("\t5. Submit medication replenishment request");
            System.out.println("\t6. View my profile");
            System.out.println("\t7. Change my password");
            System.out.println("\t8. Logout");
            System.out.println("===================================");
            System.out.println();
            System.out.print("What would you like to do? ");
            int choice = CustScanner.getIntChoice();

            try {
                switch (choice) {
                    // case 1 -> ; ////ToDo Add appointment parts and implement function here
                    case 2 -> updatePrescriptionStatus(user);
                    case 3 -> PharmacistManager.viewMedicationInventory();
                    case 4 -> PharmacistManager.viewLowStockMedicationInventory();
                    case 5 -> submitRequest(user);
                    case 6 -> ViewUserProfileDisplay.viewUserProfilePage(pharmacist, UserType.PHARMACIST);
                    case 7 -> ChangePasswordDisplay.changePassword(pharmacist, UserType.PHARMACIST);
                    case 8 -> LogoutDisplay.logout();
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
            System.out.println("");
            System.out.println("Enter Patient's email : ");
            String email = CustScanner.getStrChoice();
            Patient patient = new Patient();
            try {
                patient = PatientDatabase.getDB().getByEmail(email);
            } catch (Exception e) {
                System.out.println("Patient email not found");
                pharmacistDisplay(user);
            }
            patient.printAllDiagnosis();
            System.out.println("");
            System.out.println("Enter the diagnosis ID ");
            String diagnosisID = CustScanner.getStrChoice();
            try {
                System.out.println("The status for the diagnosis prescription is: "+patient.getOneDiagnosis(diagnosisID).getPrescription().getPrescriptionStatus());
            } catch (Exception e) {
                System.out.println("Diagnosis ID not found, check ID entered.");
                pharmacistDisplay(user);
            }
            System.out.println("Choose the options below to change to the new status: ");
            System.out.println("1. PENDING");
            System.out.println("2. DISPENSED");
            System.out.println("3. DECLINED");
            int i = CustScanner.getIntChoice();
            switch (i) {
            case 1:
            PharmacistManager.updatePrescriptionStatus(patient.getOneDiagnosis(diagnosisID), PrescriptionStatus.PENDING);
            break;
            case 2:
            PharmacistManager.updatePrescriptionStatus(patient.getOneDiagnosis(diagnosisID), PrescriptionStatus.DISPENSED);
            break;
            case 3:
            PharmacistManager.updatePrescriptionStatus(patient.getOneDiagnosis(diagnosisID), PrescriptionStatus.DECLINED);
            break;
            default:
            System.out.println("INVALID CHOICE");
            updatePrescriptionStatus(user);
            }
            UserManager.updateUser(patient);
        } catch (Exception e) {
            pharmacistDisplay(user);
        }
    }

    public static void submitRequest(User user) {
        try {
            PharmacistManager.viewMedicationInventory();
            System.out.println("");
            System.out.println("Enter the medication ID that you want to restock");
            System.out.println("");
            String id = CustScanner.getStrChoice();
            PharmacistManager.submitReplenishmentRequest(id);
        } catch (Exception e) {
            System.out.println("No such medication ID!");
        }
    }
}
