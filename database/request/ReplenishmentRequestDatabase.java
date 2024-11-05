package database.request;

import model.request.ReplenishmentRequest;

import java.util.List;
import java.util.Map;

import database.Database;

public class ReplenishmentRequestDatabase extends Database<ReplenishmentRequest> {

    private static final String FILE_PATH = "./data/user/replenishmentRequest.txt";

    ReplenishmentRequestDatabase() {
        super();
        load();
    }

    /**
     * Gets a new instance of MedicationDatabase.
     *
     * @return a new instance of MedicationDatabase
     */
    public static ReplenishmentRequestDatabase getDB() {
        return new ReplenishmentRequestDatabase();
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
            getAll().add(new ReplenishmentRequest(map));
        }
    }

    public List<ReplenishmentRequest> getAllReplenishmentRequests() {
        return super.getAll();
    }
}
