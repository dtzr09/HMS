package model.prescription.enums;

/**
 * Represents the status of a prescription in a healthcare system.
 * The statuses include:
 * <ul>
 * <li>PENDING - The prescription is awaiting processing.</li>
 * <li>DISPENSED - The prescription has been dispensed to the patient.</li>
 * <li>DECLINED - The prescription was declined and not dispensed.</li>
 * </ul>
 */
public enum PrescriptionStatus {
    PENDING, // Prescription is waiting to be processed
    DISPENSED, // Prescription has been dispensed to the patient
    DECLINED; // Prescription was declined and not dispensed

    /**
     * Converts a string representation of the prescription status to its
     * corresponding PrescriptionStatus enum value.
     *
     * @param status the string representation of the prescription status
     * @return the corresponding PrescriptionStatus value
     * @throws IllegalArgumentException if the status is not recognized
     */
    public static PrescriptionStatus fromString(String status) {
        switch (status) {
            case "PENDING":
                return PENDING;
            case "DISPENSED":
                return DISPENSED;
            case "DECLINED":
                return DECLINED;
            default:
                throw new IllegalArgumentException("Unknown prescription status: " + status);
        }
    }
}
