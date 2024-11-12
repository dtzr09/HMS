package database.request;

import model.request.ReplenishmentRequest;
import model.request.enums.RequestStatus;
import utils.utils.FormatDateTime;

import java.util.List;
import java.util.Map;

import database.Database;

/**
 * Manages the storage and retrieval of ReplenishmentRequest entities in the
 * hospital
 * management system.
 * Extends the Database class with a specific type of ReplenishmentRequest,
 * enabling
 * operations such as loading, saving, and retrieving replenishment request
 * data.
 */
public class ReplenishmentRequestDatabase extends Database<ReplenishmentRequest> {

    private static final String FILE_PATH = "./data/request/replenishmentRequest.txt";

    /**
     * Initializes a new ReplenishmentRequestDatabase instance and loads the data
     * from the
     * specified file.
     */
    ReplenishmentRequestDatabase() {
        super();
        load();
    }

    /**
     * Gets a new instance of ReplenishmentRequestDatabase.
     *
     * @return a new instance of ReplenishmentRequestDatabase
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
            String replenishmentRequestID = map.get("replenishmentRequestID");
            String dateOfModification = map.get("dateOfModification");
            String medicationID = map.get("medicationID");
            String dateOfRequest = map.get("dateOfRequest");
            String status = map.get("status");

            RequestStatus requestStatus = RequestStatus.fromString(status);

            ReplenishmentRequest replenishmentRequest = new ReplenishmentRequest(replenishmentRequestID, requestStatus,
                    FormatDateTime.convertStringToDateTime(dateOfRequest),
                    FormatDateTime.convertStringToDateTime(dateOfModification),
                    medicationID);

            getAll().add(replenishmentRequest);
        }
    }

    /**
     * Gets all replenishment requests.
     * 
     * @return a list of all replenishment requests
     */
    public List<ReplenishmentRequest> getAllReplenishmentRequests() {
        return super.getAll();
    }
}
