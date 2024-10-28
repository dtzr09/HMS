package database;

import model.medication.Medication;
import java.util.List;
import java.util.Map;

public class MedicationDatabase extends Database<Medication> {

    private static final String FILE_PATH = "./data/user/medication.txt";

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

}
