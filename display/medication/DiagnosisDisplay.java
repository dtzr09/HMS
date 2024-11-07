package display.medication;

import java.util.ArrayList;
import java.util.List;

import controller.medication.PrescriptionManager;
import controller.user.PatientManager;
import display.ClearDisplay;
import display.MedicationDisplay;
import model.diagnosis.Diagnosis;
import model.prescription.Prescription;
import model.user.Doctor;
import model.user.Patient;
import utils.exceptions.PageBackException;
import utils.iocontrol.CustScanner;

public class DiagnosisDisplay {

    public static void addDiagnosisDisplay(Patient patient, Doctor doctor) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("Add Diagnosis");
        System.out.println("--------------------------------------------");
        System.out.printf("Enter the diagnosis of the patient: ");
        String diagnosis = CustScanner.getStrChoice();

        System.out.println("Select the medications for the diagnosis.");
        MedicationDisplay.viewMedicationInventory();
        ArrayList<String> medicationIDs = new ArrayList<>();

        while (true) {
            System.out.println("Enter the medication ID: ");
            String medicationID = CustScanner.getStrChoice();
            medicationIDs.add(medicationID);

            System.out.println("Would you like to add another medication? (Y/N)");
            String choice = CustScanner.getStrChoice();
            if (choice.equalsIgnoreCase("N")) {
                break;
            }
        }

        System.out.println("Enter the instructions for drugs usage.");
        String drugInstructions = CustScanner.getStrChoice();

        Prescription prescription = null;

        try {
            prescription = PrescriptionManager.createNewPrescription(
                    medicationIDs, drugInstructions, patient.getPatientID(), doctor.getModelID());
        } catch (Exception e) {
            System.out.println("Something went wrong.");
            throw new PageBackException();
        }

        try {
            PatientManager.addDiagnosis(diagnosis, patient.getPatientID(), patient.getDoctorID(), prescription);
            System.out.println("Diagnosis added successfully.");
        } catch (Exception e) {
            System.out.println("Something went wrong.");
            throw new PageBackException();
        }
    }

    public static void updateDiagnosisDisplay(Patient patient, Doctor doctortDoctor) {
        ClearDisplay.ClearConsole();
        System.out.println("Update Diagnosis");
        System.out.println("--------------------------------------------");
        System.out.println("Current Diagnosis");
        List<Diagnosis> diagnoses = patient.getDiagnosis();
        for (Diagnosis diagnosis : diagnoses) {
            System.out.println("\t" + diagnosis.getDisease());
        }

        System.out.println("Enter the diagnosis you would like to update.");
        String diagnosis = CustScanner.getStrChoice();

        System.out.println("Select the medications for the diagnosis.");
        MedicationDisplay.viewMedicationInventory();
        ArrayList<String> medicationIDs = new ArrayList<>();

        while (true) {
            System.out.println("Enter the medication ID: ");
            String medicationID = CustScanner.getStrChoice();
            medicationIDs.add(medicationID);

            System.out.println("Would you like to add another medication? (Y/N)");
            String choice = CustScanner.getStrChoice();
            if (choice.equalsIgnoreCase("N")) {
                break;
            }
        }

        System.out.println("Enter the instructions for drugs usage.");
        String drugInstructions = CustScanner.getStrChoice();

        Prescription prescription = null;

        try {
            prescription = PrescriptionManager.createNewPrescription(
                    medicationIDs, drugInstructions, patient.getPatientID(), doctor.getModelID());
        } catch (Exception e) {
            System.out.println("Something went wrong.");
            throw new PageBackException();
        }

        try {
            PatientManager.updateDiagnosis(diagnosis, patient.getPatientID(), patient.getDoctorID(), prescription);
            System.out.println("Diagnosis updated successfully.");
        } catch (Exception e) {
            System.out.println("Something went wrong.");
            throw new PageBackException();
        }
    }
}
