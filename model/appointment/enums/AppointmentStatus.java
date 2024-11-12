package model.appointment.enums;

/**
 * Represents the various statuses an appointment can have.
 * The statuses include:
 * <ul>
 * <li>PENDING - The appointment is awaiting approval.</li>
 * <li>APPROVED - The appointment has been approved.</li>
 * <li>CANCELLED - The appointment has been cancelled by the patient or
 * doctor.</li>
 * <li>COMPLETED - The appointment has been completed.</li>
 * <li>REJECTED - The appointment has been rejected.</li>
 * </ul>
 */
public enum AppointmentStatus {
    PENDING, APPROVED, CANCELLED, COMPLETED, REJECTED;

    /**
     * Converts a string representation of the status to its corresponding
     * AppointmentStatus enum value.
     *
     * @param status the string representation of the status
     * @return the corresponding AppointmentStatus value, or null if the string
     *         does not match any known status
     */
    public static AppointmentStatus fromString(String status) {
        switch (status) {
            case "APPROVED":
                return APPROVED;
            case "PENDING":
                return PENDING;
            case "CANCELLED":
                return CANCELLED;
            case "COMPLETED":
                return COMPLETED;
            case "REJECTED":
                return REJECTED;
            default:
                return null;
        }
    }
}
