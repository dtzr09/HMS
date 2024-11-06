package controller.request;

import java.util.ArrayList;
import java.util.List;

import controller.medication.MedicationManager;
import database.request.ReplenishmentRequestDatabase;
import model.request.ReplenishmentRequest;
import model.request.enums.RequestStatus;
import utils.exceptions.ModelNotFoundException;

public class ReplenishmentRequestManager {

    public static Boolean isThereAnyPendingRequests() {
        try {
            List<ReplenishmentRequest> replenishmentRequests = ReplenishmentRequestDatabase.getDB()
                    .getAllReplenishmentRequests();
            for (ReplenishmentRequest replenishmentRequest : replenishmentRequests) {
                if (replenishmentRequest.getStatus().equals(RequestStatus.PENDING))
                    return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }

    }

    private static ReplenishmentRequest getReplenishmentRequestById(String requestId) throws ModelNotFoundException {
        return ReplenishmentRequestDatabase.getDB().getByID(requestId);
    }

    public static ArrayList<ReplenishmentRequest> viewPendingMedicationReplenishmentRequest() {
        try {
            List<ReplenishmentRequest> replenishmentRequests = ReplenishmentRequestDatabase.getDB()
                    .getAllReplenishmentRequests();
            ArrayList<ReplenishmentRequest> pendingReplenishmentRequests = new ArrayList<>();
            for (ReplenishmentRequest replenishmentRequest : replenishmentRequests) {
                if (replenishmentRequest.getStatus().equals(RequestStatus.PENDING))
                    pendingReplenishmentRequests.add(replenishmentRequest);
            }
            return pendingReplenishmentRequests;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static boolean approveMedicationReplenishmentRequest(String requestId) {
        try {
            ReplenishmentRequest replenishmentRequest = getReplenishmentRequestById(requestId);
            if (replenishmentRequest == null)
                return false;
            replenishmentRequest.setStatus(RequestStatus.APPROVED);
            MedicationManager.updateMedicationStock(replenishmentRequest.getMedicationID());
            return true;
        } catch (ModelNotFoundException e) {
            System.out.println("Request not found.");
            return false;
        }

    }

}