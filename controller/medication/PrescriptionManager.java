package controller.medication;

import model.prescription.*;
import model.prescription.enums.PrescriptionStatus;
import utils.exceptions.ModelAlreadyExistsException;
import utils.exceptions.ModelNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.medicalRecords.PrescriptionDatabase;
/**
 * The PrescriptionManager class provides utility methods for handing prescriptions such as creating and updating Prescriptions.
 */

public class PrescriptionManager {

    /**
     * Creates a new prescription and adds it to the prescription database.
     * 
     * This method attempts to add a new prescription to the database. If a prescription with the same ID
     * already exists, a `ModelAlreadyExistsException` is thrown.
     * 
     * @param prescription The `Prescription` object to be added to the database.
     * @throws ModelAlreadyExistsException If a prescription with the same ID already exists in the database.
     */
    public static void createPrescription(Prescription prescription) throws ModelAlreadyExistsException {
        PrescriptionDatabase.getDB().add(prescription);
    }

    /**
     * Updates an existing prescription in the database.
     * 
     * This method updates the details of an existing prescription in the database. If the prescription 
     * does not exist, a `ModelNotFoundException` is thrown.
     * 
     * @param newPrescription The updated `Prescription` object.
     * @throws ModelNotFoundException If the prescription to be updated does not exist in the database.
     */
    public static void updatePrescription(Prescription newPrescription) throws ModelNotFoundException {
        PrescriptionDatabase.getDB().update(newPrescription);
    }

    /**
     * Retrieves a list of all prescriptions from the database.
     * 
     * This method returns a list of all `Prescription` objects currently stored in the prescription database.
     * 
     * @return A list of all prescriptions.
     */
    public static List<Prescription> getAllPrescription() {
        return PrescriptionDatabase.getDB().getAllPrescriptions();
    }

    /**
     * Retrieves a prescription by its ID from the database.
     * 
     * This method attempts to fetch a `Prescription` object by its ID. If the prescription does not exist,
     * a `ModelNotFoundException` is thrown.
     * 
     * @param prescriptionID The ID of the prescription to retrieve.
     * @return The `Prescription` object associated with the given ID.
     * @throws ModelNotFoundException If the prescription with the given ID is not found in the database.
     */
    public static Prescription getPrescriptionByID(String prescriptionID) throws ModelNotFoundException {
        return PrescriptionDatabase.getDB().getByID(prescriptionID);
    }

    /**
     * Creates a new prescription and adds it to the database.
     * 
     * This method creates a new `Prescription` object using the provided details and adds it to the prescription database. 
     * The prescription status is set to `PENDING` by default. The prescription is then stored in the database.
     * 
     * @param prescriptionID The unique identifier for the prescription.
     * @param medicationIDs A list of medication IDs associated with the prescription.
     * @param drugInstructions The instructions for taking the prescribed medications.
     * @param patientID The ID of the patient receiving the prescription.
     * @param doctorID The ID of the doctor who issued the prescription.
     * @throws ModelAlreadyExistsException If a prescription with the same ID already exists in the database.
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
     * Updates the status of a prescription and handles stock reduction for dispensed medications.
     * 
     * This method updates the status of a prescription identified by its ID. If the new status is 
     * `DISPENSED`, it reduces the stock of each medication associated with the prescription by one. 
     * After updating the status, the method saves the updated prescription to the database.
     * 
     * @param prescriptionID The unique identifier of the prescription to be updated.
     * @param status The new status to set for the prescription.
     * @throws ModelNotFoundException If the prescription with the given ID cannot be found in the database.
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