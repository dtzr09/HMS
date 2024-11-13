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
     * Retrieves a medication by its ID from the database.
     * 
     * This method fetches a medication record from the database using its unique medication ID. If the medication
     * is not found, a `ModelNotFoundException` is thrown.
     * 
     * @param medicationID The unique ID of the medication to retrieve.
     * @return The medication object corresponding to the given medicationID.
     * @throws ModelNotFoundException If the medication with the provided ID does not exist in the database.
     */
    public static Medication getMedicationsById(String medicationID) throws ModelNotFoundException {
        return MedicationDatabase.getDB().getByID(medicationID);
    }

    /**
     * Adds a new medication to the database.
     * 
     * This method adds a new medication record to the database. If the medication already exists, a
     * `ModelAlreadyExistsException` is thrown.
     * 
     * @param medication The medication object to be added to the database.
     * @throws ModelAlreadyExistsException If a medication with the same ID already exists in the database.
     */
    public static void addMedication(Medication medication) throws ModelAlreadyExistsException {
        MedicationDatabase.getDB().add(medication);
    }

    /**
     * Updates an existing medication in the database.
     * 
     * This method updates the details of an existing medication in the database. If the medication to be updated
     * does not exist, a `ModelNotFoundException` is thrown.
     * 
     * @param medication The medication object containing the updated details.
     * @throws ModelNotFoundException If the medication to update is not found in the database.
     */
    public static void updateMedication(Medication medication) throws ModelNotFoundException {
        MedicationDatabase.getDB().update(medication);
    }

    /**
     * Checks if the medication repository is empty.
     * 
     * This method checks if there are any medications stored in the database.
     * 
     * @return `true` if the medication repository is empty, `false` otherwise.
     */
    public static boolean isRepositoryEmpty() {
        return MedicationDatabase.getDB().isEmpty();
    }

    /**
     * Loads medication data from a CSV file and adds it to the medication database.
     * 
     * This method reads medication data from the file located at `./resources/Medicine_List.csv`. For each row
     * in the CSV, it creates a new `Medication` object with the medication ID (generated using `UUID`), name,
     * stock level, and low stock level alert. The method then attempts to add the new medication to the database.
     * If the medication already exists in the database, a `ModelAlreadyExistsException` is caught and an appropriate
     * message is printed.
     * 
     * The CSV file is expected to have the following structure:
     * 1. Medication ID (which is ignored as a new one is generated)
     * 2. Medication Name
     * 3. Medication Stock
     * 4. Low Stock Level Alert
     * 
     * @throws ModelAlreadyExistsException If a medication already exists in the database with the same ID.
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
     * Retrieves all medications from the medication database.
     * 
     * This method fetches and returns a list of all medications stored in the medication database.
     * 
     * @return A list of `Medication` objects representing all medications in the database.
     */
    public static List<Medication> getAllMedications() {
        return MedicationDatabase.getDB().getAllMedications();
    }

    /**
     * Updates the stock level of a specific medication by increasing it by 10 units.
     * 
     * This method attempts to retrieve the medication by its ID and increase its stock by 10 units. 
     * If the medication is found, the stock is updated, and the `Medication` object is saved back to the database.
     * If the medication with the given ID is not found in the database, an error message is printed indicating 
     * that the medication was not found.
     * 
     * @param medicationID The ID of the medication whose stock level is to be updated.
     * @throws ModelNotFoundException If the medication with the specified ID is not found in the database.
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
     * Reduces the stock level of a specific medication by 1 unit.
     * 
     * This method attempts to retrieve the medication by its ID and decreases its stock by 1 unit. 
     * If the medication is found, the stock is updated, and the `Medication` object is saved back to the database.
     * If the medication with the given ID is not found in the database, an error message is printed indicating 
     * that the medication was not found.
     * 
     * @param medicationID The ID of the medication whose stock level is to be reduced.
     * @throws ModelNotFoundException If the medication with the specified ID is not found in the database.
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
     * Deletes a medication from the database based on the provided medication ID.
     * 
     * This method attempts to remove a medication from the database using its ID. 
     * If the medication is found in the database, it is removed successfully. 
     * If the medication with the specified ID does not exist, an error message is printed indicating 
     * that the medication was not found.
     * 
     * @param medicationID The ID of the medication to be deleted.
     * @throws ModelNotFoundException If the medication with the specified ID does not exist in the database.
     */
    public static void deleteMedication(String medicationID) {
        try {
            MedicationDatabase.getDB().remove(medicationID);
        } catch (ModelNotFoundException e) {
            System.out.println(medicationID + " not found.");
        }
    }

    /**
     * Retrieves a list of medications from the database based on a list of medication IDs.
     * 
     * This method takes a list of medication IDs and attempts to fetch the corresponding medication 
     * objects from the database. If a medication ID does not exist in the database, a ModelNotFoundException
     * will be thrown for that specific medication ID.
     * 
     * @param medicationIDs The list of medication IDs for which the medications need to be fetched.
     * @return A list of Medication objects corresponding to the provided IDs.
     * @throws ModelNotFoundException If any medication ID is not found in the database.
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
     * Retrieves the names of medications based on a list of medication IDs.
     * 
     * This method takes a list of medication IDs and attempts to fetch the corresponding medication
     * objects from the database. It then collects and returns the names of these medications.
     * If any medication ID does not exist in the database, an error message is printed for that ID.
     * 
     * @param medicationIDs The list of medication IDs for which the medication names need to be fetched.
     * @return A list of medication names corresponding to the provided medication IDs.
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
     * Retrieves the IDs of medications that are at or below their low stock level alert.
     * 
     * This method checks all medications in the database and compares their current stock levels
     * with their defined low stock level alert. If a medication's stock is at or below the alert level,
     * its ID is added to the list.
     * 
     * @return A list of medication IDs for those that are at or below the low stock level alert.
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
     * Retrieves a list of medications that are at or below their low stock level alert.
     * 
     * This method checks for medications whose stock levels are at or below the low stock level
     * threshold and returns a list of these medications.
     * It uses the medication IDs retrieved from `getLowStockIDs` and attempts to fetch the corresponding
     * `Medication` objects. If any medication is not found, the exception is caught and the stack trace is printed.
     * 
     * @return An ArrayList of `Medication` objects that have low stock.
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
