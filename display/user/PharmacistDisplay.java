package display.user;

import java.util.List;
import java.util.NoSuchElementException;

import controller.medication.DiagnosisManager;
import controller.medication.MedicationManager;
import controller.medication.PrescriptionManager;
import controller.user.PatientManager;
import controller.user.PharmacistManager;
import controller.user.UserManager;
import model.user.User;
import model.user.enums.UserType;
import display.appointment.AppointmentOutcomeDisplay;
import display.medicalRecords.DiagnosisDisplay;
import display.password.ChangePasswordDisplay;
import display.session.ClearDisplay;
import display.session.EnterToGoBackDisplay;
import display.session.LogoutDisplay;
import model.diagnosis.Diagnosis;
import model.medication.Medication;
import model.prescription.Prescription;
import model.prescription.enums.PrescriptionStatus;
import model.user.Patient;
import model.user.Pharmacist;
import utils.exceptions.PageBackException;
import utils.iocontrol.CustScanner;

public class PharmacistDisplay {

    /**
     * Display pharmacist main page
     * 
     * @param user
     */
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
                    case 1 -> viewAppointmentOutcomeRecords();
                    case 2 -> updatePrescriptionStatusDisplay(user);
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

    /**
     * Display to view appointment outcome records
     * 
     * @throws PageBackException
     */
    private static void viewAppointmentOutcomeRecords() throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("View Appointment Outcome Records");
        System.out.println("--------------------------------------");
        System.out.println();
        PatientDisplay.viewAllPatients();
        System.out.printf("Please enter patient ID or q to go back: ");
        String patientID = CustScanner.getStrChoice();
        if (patientID.equalsIgnoreCase("q")) {
            throw new PageBackException();
        }
        System.out.println();
        AppointmentOutcomeDisplay.viewAppointmentOutcomeRecordsForPharmacist(patientID);
    }

    /**
     * Display to view medication inventory
     * 
     * @throws PageBackException
     */
    public static void viewMedInv() throws PageBackException {
        viewMedicationInventory();
        EnterToGoBackDisplay.display();
    }

    /**
     * Display to view low stock medication inventory
     * 
     * @throws PageBackException
     */
    public static void viewLowMedInv() throws PageBackException {
        viewLowStockMedicationInventory();
        EnterToGoBackDisplay.display();
    }

    /**
     * Display to update prescription status
     * 
     * @param user
     * @throws PageBackException
     */
    public static void updatePrescriptionStatusDisplay(User user) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println();
        System.out.println("Update Prescription Status");
        System.out.println("-------------------------------");
        System.out.println();
        PatientDisplay.viewAllPatients();
        System.out.println();
        System.out.printf("Enter patient's ID: ");
        String patientID = CustScanner.getStrChoice();
        Patient patient = null;
        try {
            patient = PatientManager.getPatientById(patientID);
        } catch (Exception e) {
            System.out.println("Patient email not found");
            EnterToGoBackDisplay.display();
        }

        ClearDisplay.ClearConsole();
        DiagnosisDisplay.displayAllDiagnosisOfPatient(patient);
        System.out.println();
        System.out.printf("Enter the diagnosis ID: ");
        String diagnosisID = CustScanner.getStrChoice();
        Diagnosis patientDiagnosis = null;
        System.out.println();

        try {
            patientDiagnosis = DiagnosisManager.getDiagnosisByPatientIDAndDiagnosisID(patient.getPatientID(),
                    diagnosisID);
            Prescription prescription = PrescriptionManager.getPrescriptionByID(patientDiagnosis.getPrescriptionID());
            System.out.println("The status for the diagnosis prescription is: "
                    + prescription.getPrescriptionStatus());
        } catch (Exception e) {
            System.out.println("Diagnosis ID not found, check ID entered.");
            EnterToGoBackDisplay.display();
        }

        System.out.println("\t1. Pending");
        System.out.println("\t2. Dispense");
        System.out.println("\t3. Decline");
        System.out.println("\t4. Go back");
        System.out.println();
        System.out.printf("Choose from the options above to change to the new status: ");
        int i = CustScanner.getIntChoice();
        PrescriptionStatus newStatus = null;
        switch (i) {
            case 1 ->
                newStatus = PrescriptionStatus.PENDING;
            case 2 -> newStatus = PrescriptionStatus.DISPENSED;
            case 3 -> newStatus = PrescriptionStatus.DECLINED;
            case 4 -> throw new PageBackException();
            default -> {
                System.out.println("Invalid choice. Please try again.");
                updatePrescriptionStatusDisplay(user);
            }
        }
        try {
            String prescriptionID = patientDiagnosis.getPrescriptionID();
            PrescriptionManager.updatePrescriptionStatus(prescriptionID, newStatus);
            UserManager.updateUser(patient);
        } catch (Exception e) {
            System.out.println("Error updating prescription status");
        }

        EnterToGoBackDisplay.display();
    }

    /**
     * Display to submit medication replenishment request
     * 
     * @param user
     * @throws PageBackException
     */
    public static void submitRequest(User user) throws PageBackException {
        try {
            ClearDisplay.ClearConsole();
            viewMedicationInventory();
            System.out.printf("Enter the medication ID that you want to restock: ");
            String id = CustScanner.getStrChoice();
            System.out.println();
            PharmacistManager.submitReplenishmentRequest(id);
            EnterToGoBackDisplay.display();
        } catch (NoSuchElementException e) {
            System.out.println("No such medication ID!");
            EnterToGoBackDisplay.display();
        }
    }

    /**
     * View inventory
     * 
     * @param medications
     * @param title
     */
    private static void viewInventory(List<Medication> medications, String title) {
        String threeColBorder = "+--------------------------------------+----------------------+------------+";

        ClearDisplay.ClearConsole();
        System.out.printf("%-20s%n", title);
        System.out.println("----------------------------------------------------");
        System.out.println();
        System.out.println(threeColBorder);
        System.out.printf("| %-36s | %-20s | %-10s | %n", "ID", "Name", "Stock");
        System.out.println(threeColBorder);
        if (medications.isEmpty()) {
            System.out.println("| No medications found in inventory.       ");
            System.out.println(threeColBorder);
            System.out.println();
            return;
        }
        for (Medication medication : medications) {
            System.out.printf("| %-36s | %-20s | %-10s | %n", medication.getModelID(), medication.getName(),
                    medication.getStock());
        }
        System.out.println(threeColBorder);
        System.out.println();
    }

    /**
     * View medication inventory
     */
    private static void viewMedicationInventory() {
        viewInventory(MedicationManager.getAllMedications(), "Medication Inventory");
    }

    /**
     * View low stock medication inventory
     */
    private static void viewLowStockMedicationInventory() {
        viewInventory(MedicationManager.getLowStockMedicationInventory(), "Low Stock Medication Inventory");
    }

}
