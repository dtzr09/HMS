package controller.medication;

import model.medication.Medication;
import model.prescription.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class PrescriptionManager {

    public static Prescription createNewPrescription(ArrayList<String> MedicationIDs, String drugInstructions,
            String patientID,
            String doctorID) {
        ArrayList<Medication> medications = new ArrayList<Medication>();
        for (String id : MedicationIDs) {
            try {
                Medication medication = MedicationManager.findMedication(id);
                medications.add(medication);
            } catch (Exception e) {
                System.out.println("Medication not found.");
            }
        }
        String prescriptionID = UUID.randomUUID().toString();
        Date dateOfPrescription = new Date();
        Prescription prescription = new Prescription(prescriptionID, patientID, doctorID, medications,
                dateOfPrescription, drugInstructions, PrescriptionStatus.PENDING);
        return prescription;
    }

    public static Prescription updatePrescription(Prescription oldPrescription, ArrayList<String> MedicationIDs,
            String drugInstructions) {
        ArrayList<Medication> medications = new ArrayList<Medication>();
        for (String id : MedicationIDs) {
            try {
                Medication medication = MedicationManager.findMedication(id);
                medications.add(medication);
            } catch (Exception e) {
                System.out.println("Medication not found.");
            }
        }

        Date newDateOfPrescription = new Date();
        Prescription prescription = new Prescription(oldPrescription.getPrescriptionID(),
                oldPrescription.getPatientID(),
                oldPrescription.getdoctorID(), medications, newDateOfPrescription,
                drugInstructions,
                PrescriptionStatus.PENDING);

        return prescription;
    }

    // Get List of Prescriptions obj of a Diagnosis
    // public static ArrayList<Prescription>
    // getAllPrescriptionsOfDiagnosis(Diagnosis diagnosis) {
    // return diagnosis.getPrescriptions();
    // }

    // Get List of prescriptionID strings
    // public static ArrayList<String> getAllPrescriptionIDs(Diagnosis diagnosis) {
    // ArrayList<String> allPrescriptionIDs = new ArrayList<String>();
    // for (Prescription prescription : getAllPrescriptionsOfDiagnosis(diagnosis)) {
    // allPrescriptionIDs.add(prescription.getPrescriptionID());
    // }
    // return allPrescriptionIDs;
    // }

    // Get List of pending status prescriptionID strings
    // public static ArrayList<String> getAllPendingPrescriptionsIDs(Diagnosis
    // diagnosis) {
    // ArrayList<String> pendingPrescriptionIDs = new ArrayList<String>();
    // for (Prescription prescription : getAllPrescriptionsOfDiagnosis(diagnosis)) {
    // if (prescription.getPrescriptionStatus().equals(PrescriptionStatus.PENDING))
    // {
    // pendingPrescriptionIDs.add(prescription.getPrescriptionID());
    // }
    // }
    // return pendingPrescriptionIDs;
    // }

    // public static void printAllPendingPrescriptioinsIDs(Diagnosis diagnosis) {
    // for (String i : getAllPendingPrescriptionsIDs(diagnosis)) {
    // System.out.println(i);
    // }
    // }

    // // Getting corresponding index pos of a Prescription obj with its ID
    // public static int getPresciptionIndexPos(Diagnosis diagnosis, String
    // prescriptionID) {
    // return getAllPrescriptionIDs(diagnosis).indexOf(prescriptionID);
    // }

    // // Getting a particular prescription with its ID
    // public static Prescription getPresciption(Diagnosis diagnosis, String
    // prescriptionID) {
    // return diagnosis.getPrescriptions().get(getPresciptionIndexPos(diagnosis,
    // prescriptionID));
    // }

    // public ArrayList<Prescription>
    // getAllPendingPrescriptionsOfDiagnosis(Diagnosis diagnosis) {
    // ArrayList<Prescription> allPendingPrescriptions = new
    // ArrayList<Prescription>();
    // for (String id : getAllPendingPrescriptionsIDs(diagnosis)) {
    // allPendingPrescriptions.add(getPresciption(diagnosis, id));
    // }
    // return allPendingPrescriptions;
    // }

    // public static void updatePrescriptionStatus(Diagnosis diagnosis, String
    // prescriptionID, PrescriptionStatus status) {
    // getPresciption(diagnosis, prescriptionID).setPrescriptionStatus(status);
    // if (status.equals(PrescriptionStatus.DISPENSED)) {
    // for (Medication medication : getPresciption(diagnosis,
    // prescriptionID).getMedication()) {
    // MedicationManager.reduceMedicationStock(medication.getModelID());
    // }
    // }
    // }

}