package model.appointment.enums;

public enum AppointmentStatus {
    PENDING, APPROVED, CANCELLED, COMPLETED, REJECTED;

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
