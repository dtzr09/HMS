package model.request;

import java.util.Date;
import java.util.Map;

import model.Model;
import model.request.enums.RequestStatus;

public class ReplenishmentRequest implements Model {
    private String replenishmentRequestID;
    private RequestStatus status;
    private Date dateOfRequest;
    private Date dateOfModification;
    private String medicationID;

    // Constructor
    public ReplenishmentRequest(String replenishmentRequestID, RequestStatus status, Date dateOfRequest,
            Date dateOfModification, String medicationID) {
        this.replenishmentRequestID = replenishmentRequestID;
        this.status = status;
        this.dateOfRequest = dateOfRequest;
        this.dateOfModification = dateOfModification;
        this.medicationID = medicationID;
    }

    /**
     * Converts the map to a Medication object
     *
     * @param map the map
     */
    public ReplenishmentRequest(Map<String, String> map) {
        this.convertToObject(map);
    }

    public String getModelID() {
        return this.replenishmentRequestID;
    }

    public String getRequestID() {
        return replenishmentRequestID;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public boolean setStatus(RequestStatus status) {
        this.status = status;
        return true;
    }

    public Date getDateOfRequest() {
        return dateOfRequest;
    }

    public void setDateOfRequest(Date dateOfRequest) {
        this.dateOfRequest = dateOfRequest;
    }

    public Date getDateOfModification() {
        return dateOfModification;
    }

    public void setDateOfModification(Date dateOfModification) {
        this.dateOfModification = dateOfModification;
    }

    public String getMedicationID() {
        return medicationID;
    }

    public void setMedicationID(String medicationID) {
        this.medicationID = medicationID;
    }

    public void displayReplenishmentRequest() {
        System.out.println("Replenishment Request ID: " + replenishmentRequestID);
        System.out.println("Status: " + status);
        System.out.println("Date of Request: " + dateOfRequest);
        System.out.println("Date of Modification: " + dateOfModification);
        System.out.println("Medication ID: " + medicationID);
    }

}
