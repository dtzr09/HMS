package model.appointment.enums;

/**
 * Represents the days of the week, each with a string representation.
 * This enum provides methods to retrieve the weekday name, convert a string
 * to a Weekdays instance, and format the weekday name in camel case.
 */
public enum Weekdays {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final String weekday;

    /**
     * Constructs a Weekdays enum with the specified weekday name.
     *
     * @param weekday the name of the weekday
     */
    Weekdays(String weekday) {
        this.weekday = weekday;
    }

    /**
     * Retrieves the string representation of the weekday.
     *
     * @return the name of the weekday
     */
    public String getWeekday() {
        return weekday;
    }

    /**
     * Returns the Weekdays instance corresponding to the provided weekday name.
     *
     * @param weekday the string name of the weekday
     * @return the corresponding Weekdays instance, or null if no match is found
     */
    public static Weekdays getWeekday(String weekday) {
        for (Weekdays day : Weekdays.values()) {
            if (day.getWeekday().equalsIgnoreCase(weekday)) {
                return day;
            }
        }
        return null;
    }

    /**
     * Converts the enum name to a camel case representation.
     *
     * @return the weekday name in camel case format
     */
    public String toCamelCase() {
        String name = this.name().toLowerCase();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
