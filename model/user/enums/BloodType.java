package model.user.enums;

/**
 * Represents the blood type of an individual, including positive and negative
 * types,
 * as well as an option for unavailable information.
 */
public enum BloodType {

    /**
     * Blood type A positive (A+).
     */
    A_POSITIVE, // A+

    /**
     * Blood type A negative (A-).
     */
    A_NEGATIVE, // A-

    /**
     * Blood type B positive (B+).
     */
    B_POSITIVE, // B+

    /**
     * Blood type B negative (B-).
     */
    B_NEGATIVE, // B-

    /**
     * Blood type O positive (O+).
     */
    O_POSITIVE, // O+

    /**
     * Blood type O negative (O-).
     */
    O_NEGATIVE, // O-

    /**
     * Blood type AB positive (AB+).
     */
    AB_POSITIVE, // AB+

    /**
     * Blood type AB negative (AB-).
     */
    AB_NEGATIVE, // AB-

    /**
     * Not available or unspecified blood type.
     */
    NOT_AVAILABLE; // Not available or unspecified

    /**
     * Converts a string representation of a blood type to the corresponding enum
     * constant.
     *
     * @param bloodType the string representation of the blood type
     * @return the matching BloodType enum constant
     * @throws IllegalArgumentException if the blood type is unknown
     */
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
            case "A_POSITIVE":
                return A_POSITIVE;
            case "A_NEGATIVE":
                return A_NEGATIVE;
            case "B_POSITIVE":
                return B_POSITIVE;
            case "B_NEGATIVE":
                return B_NEGATIVE;
            case "O_POSITIVE":
                return O_POSITIVE;
            case "O_NEGATIVE":
                return O_NEGATIVE;
            case "AB_POSITIVE":
                return AB_POSITIVE;
            case "AB_NEGATIVE":
                return AB_NEGATIVE;
            default:
                throw new IllegalArgumentException("Unknown blood type: " + bloodType);
        }
    }
}
