package database.medicalRecords;

import java.util.List;
import java.util.Map;

import database.Database;
import model.prescription.Prescription;

public class PrescriptionDatabase extends Database<Prescription> {
    private static final String FILE_PATH = "./data/user/prescription.txt";

    PrescriptionDatabase() {
        super();
        load();
    }

    /**
     * Gets a new instance of MedicationDatabase.
     *
     * @return a new instance of MedicationDatabase
     */
    public static PrescriptionDatabase getDB() {
        return new PrescriptionDatabase();
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
            getAll().add(new Prescription(map));
        }
    }

    public List<Prescription> getAllPrescriptions() {
        return super.getAll();
    }
}
