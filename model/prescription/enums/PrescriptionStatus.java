package model.prescription.enums;

public enum PrescriptionStatus {
    PENDING, // Prescription is waiting to be processed
    DISPENSED, // Prescription has been dispensed to the patient
    DECLINED; // Prescription was declined and not dispensed

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