package database.medicalRecords;

import model.medication.Medication;

import java.util.List;
import java.util.Map;

import database.Database;

/**
 * Manages the storage and retrieval of Medication entities in the hospital
 * management system.
 * Extends the Database class with a specific type of Medication, enabling
 * operations such as loading, saving, and retrieving medication data.
 */
public class MedicationDatabase extends Database<Medication> {

    private static final String FILE_PATH = "./data/medical/medication.txt";

    /**
     * Initializes a new MedicationDatabase instance and loads the data from the
     * specified file.
     */
    MedicationDatabase() {
        super();
        load();
    }

    /**
     * Gets a new instance of MedicationDatabase.
     *
     * @return a new instance of MedicationDatabase
     */
    public static MedicationDatabase getDB() {
        return new MedicationDatabase();
    }

    /**
     * Gets the file path of the Database.
     *
     * @return the file path of the Database
     */
    @Override
    public String getFilePath() {
        return FILE_PATH;
    }

    /**
     * Sets the list of mappable objects in the Database.
     *
     * @param listOfMappableObjects the list of mappable objects to set
     */
    @Override
    public void setAll(List<Map<String, String>> listOfMappableObjects) {
        for (Map<String, String> map : listOfMappableObjects) {
            getAll().add(new Medication(map));
        }
    }

    /**
     * Gets all medications.
     * 
     * @return a list of all medications
     */
    public List<Medication> getAllMedications() {
        return super.getAll();
    }
}
