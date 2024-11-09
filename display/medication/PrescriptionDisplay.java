package display.medication;

import java.util.ArrayList;

import controller.medication.MedicationManager;
import controller.medication.PrescriptionManager;
import controller.user.PatientManager;
import display.EnterToGoBackDisplay;
import model.diagnosis.Diagnosis;
import model.medication.Medication;
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
        Prescription oldPrescription = null;
        try {
            diagnosis = PatientManager.getDiagnosisByID(patient, diagnosisId);
            oldPrescription = PrescriptionManager.getPrescriptionByID(diagnosis.getPrescriptionID());
        } catch (Exception e) {
            System.out.println("Diagnosis not found.");
            throw new PageBackException();
        }

        System.out.println("Current prescription for diagnosis id " + diagnosisId + " :");
        System.out.println("--------------------------------------------");
        System.out.println("\t Medications" + oldPrescription.getMedication().toString());
        System.out.println("\t Drug Instructions: " + oldPrescription.getDrugInstructions());

        System.out.println("Would you like to update the prescription? (Y/N)");
        String choice = CustScanner.getStrChoice();
        if (choice.equalsIgnoreCase("Y")) {
            ArrayList<Medication> medications = new ArrayList<>();
            MedicationDisplay.displayMedicationInventory();
            System.out.println();
            while (true) {
                System.out.println("Enter the medication ID: ");
                String medicationID = CustScanner.getStrChoice();
                try {
                    Medication medication = MedicationManager.getMedicationsById(medicationID);
                    medications.add(medication);
                } catch (Exception e) {
                    System.out.println("Medication not found.");
                }

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
                oldPrescription.setMedication(medications);
                PrescriptionManager.updatePrescription(oldPrescription);
                System.out.println("Prescription updated successfully.");
            } catch (Exception e) {
                System.out.println("Something went wrong.");
                throw new PageBackException();
            }
        }

        EnterToGoBackDisplay.display();
    }

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
