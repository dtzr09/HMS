package model.appointment.enums;

public enum Weekdays {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final String weekday;

    Weekdays(String weekday) {
        this.weekday = weekday;
    }

    public String getWeekday() {
        return weekday;
    }

    public static Weekdays getWeekday(String weekday) {
        for (Weekdays day : Weekdays.values()) {
            if (day.getWeekday().equalsIgnoreCase(weekday)) {
                return day;
            }
        }
        return null;
    }

    // Method to convert enum constant name to camel case (e.g., MONDAY -> Monday)
    public String toCamelCase() {
        String name = this.name().toLowerCase();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
