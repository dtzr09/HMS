package database.medicalRecords;

import java.util.List;
import java.util.Map;

import database.Database;
import model.diagnosis.Diagnosis;

/**
 * Manages the storage and retrieval of Diagnosis entities in the hospital
 * management system.
 * Extends the Database class with a specific type of Diagnosis, enabling
 * operations such as loading, saving, and retrieving diagnosis data.
 */
public class DiagnosisDatabase extends Database<Diagnosis> {
    private static final String FILE_PATH = "./data/medical/diagnosis.txt";

    /**
     * Initializes a new DiagnosisDatabase instance and loads the data from the
     * specified file.
     */
    DiagnosisDatabase() {
        super();
        load();
    }

    /**
     * Gets a new instance of DiagnosisDatabase.
     *
     * @return a new instance of DiagnosisDatabase
     */
    public static DiagnosisDatabase getDB() {
        return new DiagnosisDatabase();
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
            getAll().add(new Diagnosis(map));
        }
    }

    /**
     * Gets all diagnosis.
     *
     * @return a list of all diagnosis
     */
    public List<Diagnosis> getAllDiagnosis() {
        return super.getAll();
    }
}
