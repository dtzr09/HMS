package display.user;

import java.util.ArrayList;

import controller.user.PharmacistManager;
import controller.user.UserManager;
import model.user.User;
import model.user.enums.UserType;
import display.ClearDisplay;
import display.auth.ChangePasswordDisplay;
import display.auth.LogoutDisplay;
import model.appointment.AppointmentOutcome;
import model.medication.Medication;
import model.prescription.PrescriptionStatus;
import model.user.Patient;
import model.user.Pharmacist;
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
            System.out.println("\t7. Update my profile");
            System.out.println("\t8. Change my password");
            System.out.println("\t9. Logout");
            System.out.println("===================================");
            System.out.println();
            System.out.print("What would you like to do? ");
            int choice = CustScanner.getIntChoice();
            UserType userType = UserType.PHARMACIST;

            try {
                switch (choice) {
                    case 1 -> viewAppointmentOutcomeRecords(user);
                    case 2 -> updatePrescriptionStatus(user);
                    case 3 -> viewMedInv();
                    case 4 -> viewLowMedInv();
                    case 5 -> submitRequest(user);
                    case 6 -> UserProfileDisplay.viewUserProfilePage(pharmacist, userType);
                    case 7 -> UserProfileDisplay.updateUserProfile(pharmacist, userType);
                    case 8 -> ChangePasswordDisplay.changePassword(pharmacist, userType);
                    case 9 -> LogoutDisplay.logout();
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


    public static void viewAppointmentOutcomeRecords(User user) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("View Appointment Outcome Records");
        System.out.println("--------------------------------------");
        System.out.println();
        System.out.printf("Please enter patient email: ");
        String email = CustScanner.getStrChoice();
        System.out.println();

        try {
            Patient patient = PatientDatabase.getDB().getByEmail(email);
            String patientID = patient.getPatientID();
            ArrayList<AppointmentOutcome> recordList = PharmacistManager.getAppointmentOutcomeRecords(patientID);

            if (recordList.isEmpty() || recordList == null) {
                System.out.println("No appointment outcome records found for this patient.");
                System.out.println();
                System.out.printf("Press Enter to go back.");
                if (CustScanner.getStrChoice().equals(""))
                    throw new PageBackException();
            }

            for (AppointmentOutcome outcome : recordList) {
                System.out.println("================================================================================");
                System.out.println(outcome.getDateOfAppointment() + "   " + outcome.getTypeOfService());
                System.out.println();
                System.out.println(outcome.getConsultationNotes());
                System.out.println();
                for (Medication meds : outcome.getPrescription().getMedication()) {
                    System.out.println(meds.getName() + "   " + meds.getModelID());
                }
                System.out.println("================================================================================");
                System.out.printf("Press Enter to go back.");
                if (CustScanner.getStrChoice().equals(""))
                    throw new PageBackException();
            }
        } catch (PageBackException e) {
            throw new PageBackException();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Email not found!");
        }

    }

    public static void viewMedInv() throws PageBackException {
        PharmacistManager.viewMedicationInventory();
        System.out.println("Press Enter to go back.");
        if (CustScanner.getStrChoice().equals(""))
            throw new PageBackException();
    }

    public static void viewLowMedInv() throws PageBackException{
        PharmacistManager.viewLowStockMedicationInventory();
        System.out.println("Press Enter to go back.");
        if (CustScanner.getStrChoice().equals(""))
            throw new PageBackException();
    }

    public static void updatePrescriptionStatus(User user) throws PageBackException{
        try {
            System.out.println("");
            System.out.printf("Enter patient's email : ");
            String email = CustScanner.getStrChoice();
            Patient patient = new Patient();
            try {
                patient = PatientDatabase.getDB().getByEmail(email);
            } catch (Exception e) {
                System.out.println("Patient email not found");
                pharmacistDisplay(user);
            }
            ClearDisplay.ClearConsole();
            patient.printAllDiagnosis();
            System.out.println("");
            System.out.println("Enter the diagnosis ID: ");
            String diagnosisID = CustScanner.getStrChoice();
            try {
                System.out.println("The status for the diagnosis prescription is: "
                        + patient.getOneDiagnosis(diagnosisID).getPrescription().getPrescriptionStatus());
            } catch (Exception e) {
                System.out.println("Diagnosis ID not found, check ID entered.");
                pharmacistDisplay(user);
            }
            System.out.println("Choose the options below to change to the new status: ");
            System.out.println("1. PENDING");
            System.out.println("2. DISPENSED");
            System.out.println("3. DECLINED");
            System.out.println("4. Go back");
            int i = CustScanner.getIntChoice();
            switch (i) {
                case 1:
                    PharmacistManager.updatePrescriptionStatus(patient.getOneDiagnosis(diagnosisID),
                            PrescriptionStatus.PENDING);
                    break;
                case 2:
                    PharmacistManager.updatePrescriptionStatus(patient.getOneDiagnosis(diagnosisID),
                            PrescriptionStatus.DISPENSED);
                    break;
                case 3:
                    PharmacistManager.updatePrescriptionStatus(patient.getOneDiagnosis(diagnosisID),
                            PrescriptionStatus.DECLINED);
                    break;
                case 4:
                    throw new PageBackException();
                default:
                    System.out.println("INVALID CHOICE");
                    updatePrescriptionStatus(user);
            }
            UserManager.updateUser(patient);
            pharmacistDisplay(user);
        } catch (Exception e) {
            pharmacistDisplay(user);
        }
    }

    public static void submitRequest(User user) throws PageBackException{
        try {
            ClearDisplay.ClearConsole();
            PharmacistManager.viewMedicationInventory();
            System.out.println("============================================================================");
            System.out.println("Enter the medication ID that you want to restock");
            System.out.println("");
            String id = CustScanner.getStrChoice();
            PharmacistManager.submitReplenishmentRequest(id);
            System.out.println("Press Enter to go back.");
            if (CustScanner.getStrChoice().equals(""))
                throw new PageBackException();    
        } catch (Exception e) {
            System.out.println("No such medication ID!");
            System.out.println("Press Enter to go back.");
            if (CustScanner.getStrChoice().equals(""))
                throw new PageBackException();    
        }
    }
}
