package controller.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.medication.MedicationManager;
import database.request.ReplenishmentRequestDatabase;
import model.request.ReplenishmentRequest;
import model.request.enums.RequestStatus;
import utils.exceptions.ModelAlreadyExistsException;
import utils.exceptions.ModelNotFoundException;
/**
 * The ReplenishmentRequestManager class provides utility methods for handling Medication replenishment requests
 * between Pharmacists and Administrator.
 */

public class ReplenishmentRequestManager {

    /**
     * Checks if there are any pending replenishment requests in the database.
     * 
     * This method retrieves all replenishment requests and checks if any of them have a status of 
     * `PENDING`. If at least one pending request is found, the method returns `true`. If no pending 
     * requests are found or if an error occurs during the process, it returns `false`.
     * 
     * @return `true` if there is at least one pending replenishment request, `false` otherwise.
     */
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

    /**
     * Updates an existing replenishment request in the database.
     * 
     * This method updates the details of a replenishment request in the database. The updated request
     * must be passed as an argument, and its changes will be reflected in the database.
     * 
     * @param request the replenishment request to be updated.
     * @throws ModelNotFoundException if the replenishment request does not exist in the database.
     */
    public static void updateRequst(ReplenishmentRequest request) throws ModelNotFoundException {
        ReplenishmentRequestDatabase.getDB().update(request);
    }

    /**
     * Adds a new replenishment request to the database.
     * 
     * This method creates a new replenishment request and adds it to the database. If the request details
     * are valid and not already existing, it will be added to the database.
     * 
     * @param replenishmentRequestID the unique ID of the replenishment request.
     * @param status the current status of the replenishment request (e.g., PENDING, APPROVED, etc.).
     * @param dateOfRequest the date when the replenishment request was made.
     * @param dateOfModification the date when the request was last modified.
     * @param medicationID the ID of the medication associated with the replenishment request.
     * @throws ModelAlreadyExistsException if a replenishment request with the same ID already exists.
     */
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

    /**
     * Retrieves a replenishment request by its unique ID.
     * 
     * This method fetches a specific replenishment request from the database using its request ID.
     * If the request ID does not exist in the database, it will throw a ModelNotFoundException.
     * 
     * @param requestId the unique ID of the replenishment request to retrieve.
     * @return the ReplenishmentRequest object associated with the given request ID.
     * @throws ModelNotFoundException if the replenishment request with the given ID is not found.
     */
    private static ReplenishmentRequest getReplenishmentRequestById(String requestId) throws ModelNotFoundException {
        return ReplenishmentRequestDatabase.getDB().getByID(requestId);
    }

    /**
     * Retrieves a list of all pending medication replenishment requests.
     * 
     * This method checks the database for all replenishment requests with a status of PENDING and
     * returns them as a list. If no pending requests are found, it returns an empty list.
     * 
     * @return an ArrayList of ReplenishmentRequest objects with a PENDING status.
     */
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

    /**
     * Approves a medication replenishment request and updates the medication stock.
     * 
     * This method retrieves a replenishment request by its ID, updates its status to APPROVED, 
     * and then increases the stock of the corresponding medication. It also updates the replenishment 
     * request in the database after the approval process.
     * 
     * @param requestId the unique ID of the replenishment request to approve.
     * @return true if the replenishment request was successfully approved and stock updated; 
     *         false if the request was not found or an error occurred.
     */
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

    /**
     * Declines a medication replenishment request by setting its status to REJECTED.
     * 
     * This method retrieves a replenishment request by its ID and updates its status to REJECTED. 
     * The updated request is then stored in the database. If the request cannot be found, 
     * the method returns false. If the process is successful, it returns true.
     * 
     * @param requestId the unique ID of the replenishment request to decline.
     * @return true if the replenishment request was successfully declined; 
     *         false if the request was not found or an error occurred.
     */
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