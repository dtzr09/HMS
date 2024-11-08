package model.request.enums;

public enum RequestStatus {
    PENDING, APPROVED, REJECTED;

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
