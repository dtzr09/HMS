package display.medicalRecords;

import java.util.ArrayList;
import java.util.List;

import controller.medication.DiagnosisManager;
import controller.medication.MedicationManager;
import controller.medication.PrescriptionManager;
import display.session.EnterToGoBackDisplay;
import model.diagnosis.Diagnosis;
import model.medication.Medication;
import model.prescription.Prescription;
import model.user.Doctor;
import model.user.Patient;
import utils.exceptions.PageBackException;
import utils.iocontrol.CustScanner;

/**
 * The PrescriptionDisplay class provides display methods for managing
 * prescriptions,
 * including updating and adding new prescriptions for a patient. It interacts
 * with
 * various managers to retrieve and update prescription and medication
 * information.
 */
public class PrescriptionDisplay {
    /**
     * Displays the menu to update an existing prescription for a patient's
     * diagnosis.
     * Shows the current prescription details, allows the user to add medications,
     * and update drug instructions.
     *
     * @param patient     the patient associated with the prescription
     * @param doctor      the doctor updating the prescription
     * @param diagnosisId the ID of the diagnosis associated with the prescription
     * @throws PageBackException if the diagnosis is not found or another error
     *                           occurs
     */
    public static void updatePrescriptionDisplay(Patient patient, Doctor doctor,
            String diagnosisId)
            throws PageBackException {

        Diagnosis diagnosis = null;
        Prescription oldPrescription = null;
        String medicationsStr = "";
        try {
            diagnosis = DiagnosisManager.getDiagnosisByID(patient, diagnosisId);
            oldPrescription = PrescriptionManager.getPrescriptionByID(diagnosis.getPrescriptionID());
            ArrayList<Medication> medication = MedicationManager
                    .getMedicationsByIDs(oldPrescription.getMedicationIDs());
            List<String> medicationNames = new ArrayList<>();
            for (Medication med : medication) {
                medicationNames.add(med.getName());
            }
            medicationsStr = String.join(",", medicationNames);
        } catch (Exception e) {
            System.out.println("Diagnosis not found.");
            throw new PageBackException();
        }

        System.out.println("Current prescription for diagnosis id " + diagnosisId + " :");
        System.out.println("--------------------------------------------");
        System.out.println("\t Medications" + medicationsStr);
        System.out.println("\t Drug Instructions: " + oldPrescription.getDrugInstructions());

        System.out.println("Would you like to update the prescription? (Y/N)");
        String choice = CustScanner.getStrChoice();
        if (choice.equalsIgnoreCase("Y")) {
            ArrayList<String> medicationIDs = new ArrayList<>();
            MedicationDisplay.displayMedicationInventory();
            System.out.println();
            while (true) {
                System.out.println("Enter the medication ID: ");
                String medicationID = CustScanner.getStrChoice();
                medicationIDs.add(medicationID);
                System.out.println();
                System.out.println("Would you like to add another medication? (Y/N)");
                choice = CustScanner.getStrChoice();
                if (choice.equalsIgnoreCase("N")) {
                    break;
                }
            }

            System.out.println("Enter the instructions for drugs usage.");
            String drugInstructions = CustScanner.getStrChoice();

            try {
                oldPrescription.setDrugInstructions(drugInstructions);
                oldPrescription.setMedicationIDs(medicationIDs);
                PrescriptionManager.updatePrescription(oldPrescription);
                System.out.println("Prescription updated successfully.");
            } catch (Exception e) {
                System.out.println("Something went wrong.");
                throw new PageBackException();
            }
        }

        EnterToGoBackDisplay.display();
    }

    /**
     * Displays the menu to add a new prescription for a diagnosis.
     * Prompts the user to select medications and enter drug instructions,
     * and then creates a new prescription associated with the specified patient and
     * doctor.
     *
     * @param patient        the patient for whom the prescription is being created
     * @param doctor         the doctor creating the prescription
     * @param prescriptionID the unique ID for the new prescription
     * @throws PageBackException if an error occurs while creating the prescription
     */
    public static void displayAddNewPresciption(Patient patient, Doctor doctor, String prescriptionID)
            throws PageBackException {
        System.out.println("Select the medications for the diagnosis.");
        System.out.println();
        MedicationDisplay.displayMedicationInventory();
        ArrayList<String> medicationIDs = new ArrayList<>();

        while (true) {
            System.out.printf("Enter the medication ID: ");
            String medicationID = CustScanner.getStrChoice();
            medicationIDs.add(medicationID);
            System.out.println();

            System.out.printf("Would you like to add another medication? (Y/N) ");
            String choice = CustScanner.getStrChoice();
            if (choice.equalsIgnoreCase("N")) {
                break;
            }
        }

        System.out.println();
        System.out.printf("Enter the instructions for drugs usage. ");
        String drugInstructions = CustScanner.getStrChoice();

        try {
            PrescriptionManager.createNewPrescription(prescriptionID,
                    medicationIDs, drugInstructions, patient.getPatientID(), doctor.getModelID());
        } catch (Exception e) {
            System.out.println("Something went wrong.");
            throw new PageBackException();
        }
    }

}
