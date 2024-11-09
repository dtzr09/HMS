package model.user.enums;

public enum BloodType {
    A_POSITIVE, // A+
    A_NEGATIVE, // A-
    B_POSITIVE, // B+
    B_NEGATIVE, // B-
    O_POSITIVE, // O+
    O_NEGATIVE, // O-
    AB_POSITIVE, // AB+
    AB_NEGATIVE, // AB-
    NOT_AVAILABLE;

    public static BloodType fromString(String bloodType) {
        switch (bloodType) {
            case "A+":
                return A_POSITIVE;
            case "A-":
                return A_NEGATIVE;
            case "B+":
                return B_POSITIVE;
            case "B-":
                return B_NEGATIVE;
            case "O+":
                return O_POSITIVE;
            case "O-":
                return O_NEGATIVE;
            case "AB+":
                return AB_POSITIVE;
            case "AB-":
                return AB_NEGATIVE;
            case "null":
            case "NOT_AVAILABLE":
                return NOT_AVAILABLE;
            default:
                throw new IllegalArgumentException("Unknown blood type: " + bloodType);
        }
    }
}
