package model.user;

import java.util.Date;

public class ReplenishmentRequest {
    private String replenishmentRequestID;
    private RequestStatus status;
    private Date dateOfRequest;
    private Date dateOfModification;

    // Constructor
    public ReplenishmentRequest(String replenishmentRequestID, RequestStatus status, Date dateOfRequest,
            Date dateOfModification) {
        this.replenishmentRequestID = replenishmentRequestID;
        this.status = status;
        this.dateOfRequest = dateOfRequest;
        this.dateOfModification = dateOfModification;
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

    public void displayReplenishmentRequest() {
        System.out.println("Replenishment Request ID: " + replenishmentRequestID);
        System.out.println("Status: " + status);
        System.out.println("Date of Request: " + dateOfRequest);
        System.out.println("Date of Modification: " + dateOfModification);
    }
}
