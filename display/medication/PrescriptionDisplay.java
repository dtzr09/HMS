package display.medication;

import java.util.ArrayList;

import controller.medication.PrescriptionManager;
import controller.user.PatientManager;
import display.EnterToGoBackDisplay;
import model.diagnosis.Diagnosis;
import model.prescription.Prescription;
import model.user.Doctor;
import model.user.Patient;
import utils.exceptions.PageBackException;
import utils.iocontrol.CustScanner;

public class PrescriptionDisplay {
    public static void updatePrescriptionDisplay(Patient patient, Doctor doctor,
            String diagnosisId)
            throws PageBackException {

        Diagnosis diagnosis = null;
        try {
            diagnosis = PatientManager.getDiagnosisByID(patient, diagnosisId);
        } catch (Exception e) {
            System.out.println("Diagnosis not found.");
            throw new PageBackException();
        }

        Prescription oldPrescription = diagnosis.getPrescription();

        System.out.println("Current prescription for diagnosis id " + diagnosisId + " :");
        System.out.println("--------------------------------------------");
        System.out.println("\t Medications" + oldPrescription.getMedication().toString());
        System.out.println("\t Drug Instructions: " + oldPrescription.getDrugInstructions());

        System.out.println("Would you like to update the prescription? (Y/N)");
        String choice = CustScanner.getStrChoice();
        if (choice.equalsIgnoreCase("Y")) {
            ArrayList<String> medicationIDs = new ArrayList<>();

            while (true) {
                System.out.println("Enter the medication ID: ");
                String medicationID = CustScanner.getStrChoice();
                medicationIDs.add(medicationID);

                System.out.println("Would you like to add another medication? (Y/N)");
                choice = CustScanner.getStrChoice();
                if (choice.equalsIgnoreCase("N")) {
                    break;
                }
            }

            System.out.println("Enter the instructions for drugs usage.");
            String drugInstructions = CustScanner.getStrChoice();

            try {
                PatientManager.updatePrescription(diagnosis, patient, oldPrescription, medicationIDs, drugInstructions);
                System.out.println("Prescription updated successfully.");
            } catch (Exception e) {
                System.out.println("Something went wrong.");
                throw new PageBackException();
            }
        }

        EnterToGoBackDisplay.display();
    }

    public static Prescription displayAddNewPresciption(Patient patient, Doctor doctor) throws PageBackException {
        System.out.println("Select the medications for the diagnosis.");
        System.out.println();
        MedicationDisplay.displayMedicationInventory();
        ArrayList<String> medicationIDs = new ArrayList<>();

        while (true) {
            System.out.printf("Enter the medication ID: ");
            String medicationID = CustScanner.getStrChoice();
            medicationIDs.add(medicationID);

            System.out.printf("Would you like to add another medication? (Y/N) ");
            String choice = CustScanner.getStrChoice();
            if (choice.equalsIgnoreCase("N")) {
                break;
            }
        }

        System.out.printf("Enter the instructions for drugs usage. ");
        String drugInstructions = CustScanner.getStrChoice();

        Prescription prescription = null;

        try {
            prescription = PrescriptionManager.createNewPrescription(
                    medicationIDs, drugInstructions, patient.getPatientID(), doctor.getModelID());
        } catch (Exception e) {
            System.out.println("Something went wrong.");
            throw new PageBackException();
        }

        return prescription;
    }

}
