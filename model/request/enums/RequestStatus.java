package model.request.enums;

/**
 * Enum representing the status of a request in the system.
 * Possible statuses include:
 * <ul>
 * <li>PENDING: The request is awaiting review.</li>
 * <li>APPROVED: The request has been approved.</li>
 * <li>REJECTED: The request has been rejected.</li>
 * </ul>
 */
public enum RequestStatus {
    /**
     * The request is awaiting review.
     */
    PENDING,

    /**
     * The request has been approved.
     */
    APPROVED,

    /**
     * The request has been rejected.
     */
    REJECTED;

    /**
     * Converts a string representation of a request status to a RequestStatus enum.
     *
     * @param status the string representation of the status
     * @return the corresponding RequestStatus
     * @throws IllegalArgumentException if the status does not match any known
     *                                  values
     */
    public static RequestStatus fromString(String status) {
        switch (status) {
            case "PENDING":
                return PENDING;
            case "APPROVED":
                return APPROVED;
            case "REJECTED":
                return REJECTED;
            default:
                throw new IllegalArgumentException("Unknown request status: " + status);
        }
    }
}
