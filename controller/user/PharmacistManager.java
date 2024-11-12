package controller.user;

import controller.request.ReplenishmentRequestManager;
import model.request.enums.RequestStatus;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.UUID;

public class PharmacistManager {
    /**
     * Submit replenishment request
     * 
     * @param medicationID
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
