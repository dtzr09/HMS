package model.appointment.enums;

public enum AppointmentOutcomeStatus {
    PENDING, COMPLETED;

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
