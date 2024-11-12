package model.appointment.enums;

/**
 * Represents the status of an appointment outcome.
 * The status can either be:
 * <ul>
 * <li>PENDING - The outcome is pending and has not been finalized.</li>
 * <li>COMPLETED - The outcome is completed and finalized.</li>
 * </ul>
 */
public enum AppointmentOutcomeStatus {
    PENDING, COMPLETED;

    /**
     * Converts a string representation of the status to its corresponding
     * AppointmentOutcomeStatus enum value.
     *
     * @param status the string representation of the status
     * @return the corresponding AppointmentOutcomeStatus value, or null if the
     *         string
     *         does not match any known status
     */
    public static AppointmentOutcomeStatus fromString(String status) {
        switch (status) {
            case "PENDING":
                return PENDING;
            case "COMPLETED":
                return COMPLETED;
            default:
                return null;
        }
    }
}
