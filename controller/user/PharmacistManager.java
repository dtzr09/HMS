package controller.user;

import controller.request.ReplenishmentRequestManager;
import model.request.enums.RequestStatus;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.UUID;

public class PharmacistManager {
    /**
     * Submits a replenishment request for a specified medication.
     * 
     * This method creates a new replenishment request with a unique request ID and the current date. The status
     * of the request is initially set to `PENDING`. The request is then added to the `ReplenishmentRequestManager`.
     * If any exception occurs during the process, a `NoSuchElementException` is thrown.
     * 
     * @param medicationID the unique ID of the medication for which the replenishment request is being submitted.
     * @throws NoSuchElementException if an error occurs while adding the replenishment request.
     */
    public static void submitReplenishmentRequest(String medicationID) {
        String replenishmentRequestID = UUID.randomUUID().toString();
        Date dateOfRequest = new Date();
        try {
            ReplenishmentRequestManager.addReplenishmentRequest(replenishmentRequestID, RequestStatus.PENDING,
                    dateOfRequest, dateOfRequest, medicationID);
        } catch (Exception e) {
            throw new NoSuchElementException();
        }
    }

}
