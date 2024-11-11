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

}