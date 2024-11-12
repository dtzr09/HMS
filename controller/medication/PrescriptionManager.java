package controller.medication;

import model.prescription.*;
import model.prescription.enums.PrescriptionStatus;
import utils.exceptions.ModelAlreadyExistsException;
import utils.exceptions.ModelNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.medicalRecords.PrescriptionDatabase;

public class PrescriptionManager {

    /**
     * Create a new prescription
     * 
     * @param prescription
     * @throws ModelAlreadyExistsException
     */
    public static void createPrescription(Prescription prescription) throws ModelAlreadyExistsException {
        PrescriptionDatabase.getDB().add(prescription);
    }

    /**
     * Update a prescription
     * 
     * @param newPrescription
     * @throws ModelNotFoundException
     */
    public static void updatePrescription(Prescription newPrescription) throws ModelNotFoundException {
        PrescriptionDatabase.getDB().update(newPrescription);
    }

    /**
     * Get all prescriptions
     * 
     * @return List of prescriptions
     */
    public static List<Prescription> getAllPrescription() {
        return PrescriptionDatabase.getDB().getAllPrescriptions();
    }

    /**
     * Get a prescription by its ID
     * 
     * @param prescriptionID
     * @return Prescription
     * @throws ModelNotFoundException
     */
    public static Prescription getPrescriptionByID(String prescriptionID) throws ModelNotFoundException {
        return PrescriptionDatabase.getDB().getByID(prescriptionID);
    }

    /**
     * Create a new prescription
     * 
     * @param prescriptionID
     * @param medicationIDs
     * @param drugInstructions
     * @param patientID
     * @param doctorID
     * @throws ModelAlreadyExistsException
     */
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

    /**
     * Update prescription status 
     * 
     * @param prescriptionID
     * @param status
     */
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