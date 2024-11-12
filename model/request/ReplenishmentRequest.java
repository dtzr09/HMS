package model.request;

import java.util.Date;
import java.util.Map;

import model.Model;
import model.request.enums.RequestStatus;

/**
 * Represents a replenishment request for medication inventory in the system.
 * This class provides information about the request ID, status, request date,
 * modification date, and associated medication ID.
 */
public class ReplenishmentRequest implements Model {
    private String replenishmentRequestID;
    private RequestStatus status;
    private Date dateOfRequest;
    private Date dateOfModification;
    private String medicationID;

    /**
     * Constructs a ReplenishmentRequest with all specified fields.
     *
     * @param replenishmentRequestID the unique identifier for the replenishment
     *                               request.
     * @param status                 the current status of the replenishment
     *                               request.
     * @param dateOfRequest          the date the request was created.
     * @param dateOfModification     the date the request was last modified.
     * @param medicationID           the unique identifier of the medication for
     *                               replenishment.
     */
    public ReplenishmentRequest(String replenishmentRequestID, RequestStatus status, Date dateOfRequest,
            Date dateOfModification, String medicationID) {
        this.replenishmentRequestID = replenishmentRequestID;
        this.status = status;
        this.dateOfRequest = dateOfRequest;
        this.dateOfModification = dateOfModification;
        this.medicationID = medicationID;
    }

    /**
     * Constructs a ReplenishmentRequest by converting a map of attributes.
     *
     * @param map a map of replenishment request attributes.
     */
    public ReplenishmentRequest(Map<String, String> map) {
        this.convertToObject(map);
    }

    /**
     * Retrieves the unique identifier for the replenishment request.
     *
     * @return the ID of the replenishment request.
     */
    public String getModelID() {
        return this.replenishmentRequestID;
    }

    /**
     * Retrieves the request ID.
     *
     * @return the unique identifier for the replenishment request.
     */
    public String getRequestID() {
        return replenishmentRequestID;
    }

    /**
     * Retrieves the current status of the replenishment request.
     *
     * @return the status of the replenishment request.
     */
    public RequestStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the replenishment request.
     *
     * @param status the new status to set.
     * @return true if the status was set successfully.
     */
    public boolean setStatus(RequestStatus status) {
        this.status = status;
        return true;
    }

    /**
     * Retrieves the date the replenishment request was created.
     *
     * @return the date of the request.
     */
    public Date getDateOfRequest() {
        return dateOfRequest;
    }

    /**
     * Sets the date the replenishment request was created.
     *
     * @param dateOfRequest the date of the request to set.
     */
    public void setDateOfRequest(Date dateOfRequest) {
        this.dateOfRequest = dateOfRequest;
    }

    /**
     * Retrieves the date the replenishment request was last modified.
     *
     * @return the date of modification.
     */
    public Date getDateOfModification() {
        return dateOfModification;
    }

    /**
     * Sets the date the replenishment request was last modified.
     *
     * @param dateOfModification the date of modification to set.
     */
    public void setDateOfModification(Date dateOfModification) {
        this.dateOfModification = dateOfModification;
    }

    /**
     * Retrieves the medication ID associated with the replenishment request.
     *
     * @return the medication ID.
     */
    public String getMedicationID() {
        return medicationID;
    }

    /**
     * Sets the medication ID associated with the replenishment request.
     *
     * @param medicationID the medication ID to set.
     */
    public void setMedicationID(String medicationID) {
        this.medicationID = medicationID;
    }
}
