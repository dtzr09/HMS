package controller.medication;

import model.prescription.*;
import utils.exceptions.ModelAlreadyExistsException;
import utils.exceptions.ModelNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.medicalRecords.PrescriptionDatabase;

public class PrescriptionManager {
    public static void createPrescription(Prescription prescription) throws ModelAlreadyExistsException {
        PrescriptionDatabase.getDB().add(prescription);
    }

    public static void updatePrescription(Prescription newPrescription) throws ModelNotFoundException {
        PrescriptionDatabase.getDB().update(newPrescription);
    }

    public static List<Prescription> getAllPrescription() {
        return PrescriptionDatabase.getDB().getAllPrescriptions();
    }

    public static Prescription getPrescriptionByID(String prescriptionID) throws ModelNotFoundException {
        return PrescriptionDatabase.getDB().getByID(prescriptionID);
    }

    public static void createNewPrescription(String prescriptionID, ArrayList<String> medicationIDs,
            String drugInstructions,
            String patientID,
            String doctorID) throws ModelAlreadyExistsException {
        Date dateOfPrescription = new Date();
        Prescription prescription = new Prescription(prescriptionID, patientID,
                doctorID, medicationIDs,
                dateOfPrescription, drugInstructions, PrescriptionStatus.PENDING);

        createPrescription(prescription);
    }

    public static void updatePrescriptionStatus(String prescriptionID, PrescriptionStatus status) {
        try {
            Prescription prescription = getPrescriptionByID(prescriptionID);
            prescription.setPrescriptionStatus(status);
            if (status.equals(PrescriptionStatus.DISPENSED)) {
                for (String medicationID : prescription.getMedicationIDs()) {
                    MedicationManager.reduceMedicationStock(medicationID);
                }
            }
            updatePrescription(prescription);
        } catch (ModelNotFoundException e) {
            System.out.println("Prescription not found.");
        }
    }

    // public static Prescription updatePrescription(Prescription oldPrescription,
    // ArrayList<String> MedicationIDs,
    // String drugInstructions) {
    // ArrayList<Medication> medications = new ArrayList<Medication>();
    // for (String id : MedicationIDs) {
    // try {
    // Medication medication = MedicationManager.findMedication(id);
    // medications.add(medication);
    // } catch (Exception e) {
    // System.out.println("Medication not found.");
    // }
    // }

    // Date newDateOfPrescription = new Date();
    // Prescription prescription = new
    // Prescription(oldPrescription.getPrescriptionID(),
    // oldPrescription.getPatientID(),
    // oldPrescription.getdoctorID(), medications, newDateOfPrescription,
    // drugInstructions,
    // PrescriptionStatus.PENDING);

    // return prescription;
    // }

    // public static void updatePrescriptionStatus(Prescription prescription,
    // PrescriptionStatus status) {
    // prescription.setPrescriptionStatus(status);
    // if (status.equals(PrescriptionStatus.DISPENSED)) {
    // for (Medication medication : prescription.getMedication()) {
    // MedicationManager.reduceMedicationStock(medication.getModelID());
    // }
    // }
    // }

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