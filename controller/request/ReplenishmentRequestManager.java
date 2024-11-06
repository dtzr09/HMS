package controller.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.medication.MedicationManager;
import database.medication.MedicationDatabase;
import database.request.ReplenishmentRequestDatabase;
import model.medication.Medication;
import model.request.ReplenishmentRequest;
import model.request.enums.RequestStatus;
import utils.exceptions.ModelAlreadyExistsException;
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

    public static void updateRequst(ReplenishmentRequest request) throws ModelNotFoundException {
        ReplenishmentRequestDatabase.getDB().update(request);
    }

    public static void addReplenishmentRequest(String replenishmentRequestID, RequestStatus status, Date dateOfRequest,
            Date dateOfModification, String medicationID) {
        ReplenishmentRequest request = new ReplenishmentRequest(replenishmentRequestID, status, dateOfRequest,
                dateOfModification, medicationID);
        try {
            ReplenishmentRequestDatabase.getDB().add(request);
        } catch (ModelAlreadyExistsException e) {
            System.out.println("Request Invalid. Check request details before submitting!");
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
            updateRequst(replenishmentRequest);
            return true;
        } catch (ModelNotFoundException e) {
            System.out.println("Request not found.");
            return false;
        }

    }

    public static boolean declineMedicationReplenishmentRequest(String requestId) {
        try {
            ReplenishmentRequest replenishmentRequest = getReplenishmentRequestById(requestId);
            if (replenishmentRequest == null)
                return false;
            replenishmentRequest.setStatus(RequestStatus.REJECTED);
            updateRequst(replenishmentRequest);
            return true;
        } catch (ModelNotFoundException e) {
            System.out.println("Request not found.");
            return false;
        }
    }

}