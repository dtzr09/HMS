package controller.medication;

import model.medication.Medication;
import utils.exceptions.ModelAlreadyExistsException;
import utils.exceptions.ModelNotFoundException;
import utils.iocontrol.CSVReader;
import java.util.UUID;

import database.medicalRecords.MedicationDatabase;

import java.util.ArrayList;
import java.util.List;

public class MedicationManager {

    /**
     * Find a medication by its ID
     * 
     * @param medicationID
     * @return Medication
     * @throws ModelNotFoundException
     */
    public static Medication getMedicationsById(String medicationID) throws ModelNotFoundException {
        return MedicationDatabase.getDB().getByID(medicationID);
    }

    /**
     * Add a medication to the database
     * 
     * @param medication
     * @throws ModelAlreadyExistsException
     */
    public static void addMedication(Medication medication) throws ModelAlreadyExistsException {
        MedicationDatabase.getDB().add(medication);
    }

    /**
     * Update a medication in the database
     * 
     * @param medication
     * @throws ModelNotFoundException
     */
    public static void updateMedication(Medication medication) throws ModelNotFoundException {
        MedicationDatabase.getDB().update(medication);
    }

    /**
     * Check if the medication database is empty
     * 
     * @return true if the medication database is empty, false otherwise
     */
    public static boolean isRepositoryEmpty() {
        return MedicationDatabase.getDB().isEmpty();
    }

    /**
     * Load medications from a CSV file
     */
    public static void loadMedication() {
        List<List<String>> medications = CSVReader.read("./resources/Medicine_List.csv", true);
        for (List<String> medication : medications) {
            try {
                String medicationID = UUID.randomUUID().toString();
                String medicationName = medication.get(1);
                int medicationStock = Integer.parseInt(medication.get(2));
                int lowStockLevelAlert = Integer.parseInt(medication.get(3));
                addMedication(new Medication(medicationID, medicationName, medicationStock, lowStockLevelAlert));
            } catch (ModelAlreadyExistsException e) {
                System.out.println("Medication already exists.");
            }
        }
    }

    /**
     * Get all medications from the database
     * 
     * @return List of Medication
     */
    public static List<Medication> getAllMedications() {
        return MedicationDatabase.getDB().getAllMedications();
    }

    /**
     * Update medication stock by adding 10
     * 
     * @param medicationID
     */
    public static void updateMedicationStock(String medicationID) {
        try {
            Medication medication = getMedicationsById(medicationID);
            medication.setStock(medication.getStock() + 10);
            updateMedication(medication);
        } catch (ModelNotFoundException e) {
            System.out.println(medicationID + " not found.");
        }
    }

    /**
     * Reduce medication stock by 1
     * 
     * @param medicationID
     */
    public static void reduceMedicationStock(String medicationID) {
        try {
            Medication medication = getMedicationsById(medicationID);
            medication.setStock(medication.getStock() - 1);
            updateMedication(medication);
        } catch (ModelNotFoundException e) {
            System.out.println(medicationID + " not found.");
        }
    }

    /**
     * Delete a medication from the database
     * 
     * @param medicationID
     */
    public static void deleteMedication(String medicationID) {
        try {
            MedicationDatabase.getDB().remove(medicationID);
        } catch (ModelNotFoundException e) {
            System.out.println(medicationID + " not found.");
        }
    }

    /**
     * Get medications from database by their IDs
     * 
     * @param patientID
     * @return List of Medication
     */
    public static ArrayList<Medication> getMedicationsByIDs(ArrayList<String> medicationIDs)
            throws ModelNotFoundException {
        ArrayList<Medication> medications = new ArrayList<>();
        for (String medicationID : medicationIDs) {
            medications.add(getMedicationsById(medicationID));
        }
        return medications;
    }

    /**
     * Get medication names from database by their IDs
     * 
     * @param patientID
     * @return List of Medication names
     */
    public static ArrayList<String> getListOfMedicationNamesByIDs(List<String> medicationIDs) {
        ArrayList<String> medicationsNames = new ArrayList<>();
        for (String medicationID : medicationIDs) {
            try {
                Medication medication = getMedicationsById(medicationID);
                medicationsNames.add(medication.getName());
            } catch (ModelNotFoundException e) {
                System.out.println(medicationID + " not found.");
            }
        }
        return medicationsNames;
    }

    /**
     * Get low stock IDs
     * 
     * @return
     */
    private static ArrayList<String> getLowStockIDs() {
        ArrayList<String> idList = new ArrayList<String>();
        for (Medication i : getAllMedications()) {
            if (i.getStock() <= i.getLowStockLevelAlert()) {
                idList.add(i.getModelID());
            }
        }
        return idList;
    }

    /**
     * Get low stock medication inventory
     * 
     * @return
     */
    public static ArrayList<Medication> getLowStockMedicationInventory() {
        ArrayList<Medication> medList = new ArrayList<Medication>();
        for (String id : getLowStockIDs()) {
            try {
                medList.add(MedicationManager.getMedicationsById(id));
            } catch (ModelNotFoundException e) {
                e.printStackTrace();
            }
        }
        return medList;
    }
}
