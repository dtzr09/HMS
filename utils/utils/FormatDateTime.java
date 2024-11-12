package utils.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * Utility class for handling date and time formatting and conversions.
 */
public class FormatDateTime {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Formats a Date object to a string in "dd/MM/yyyy" format.
     *
     * @param date the Date to format
     * @return a string representation of the date in "dd/MM/yyyy" format
     */
    public static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    /**
     * Formats a Date object to a string in "dd/MM/yyyy hh:mm a" format.
     *
     * @param date the Date to format
     * @return a string representation of the date and time in "dd/MM/yyyy hh:mm a"
     *         format
     */
    public static String formatDateTimeToString(Date date) {
        SimpleDateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        return outputFormatter.format(date);
    }

    /**
     * Converts a date string in "EEE MMM dd HH:mm:ss z yyyy" format to a Date
     * object.
     * <p>
     * This method expects the date string to be in a specific format and returns
     * null if parsing fails.
     * </p>
     *
     * @param dateString the date string in "EEE MMM dd HH:mm:ss z yyyy" format
     * @return a Date object representing the date, or null if parsing fails
     */
    public static Date convertStringToDateTime(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        formatter.setLenient(false);

        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Converts day, month, and year values to a Date object.
     *
     * @param date  the day of the month (1-31)
     * @param month the month (1-12)
     * @param year  the year
     * @return a Date object representing the specified day, month, and year
     */
    public static Date convertDMYToTime(int date, int month, int year) {
        LocalDate fullDate = LocalDate.of(year, month, date);
        return java.sql.Date.valueOf(fullDate);
    }

    /**
     * Formats a Date object to a string in "yyyy-MM-dd" format.
     *
     * @param date the Date to format
     * @return a string representation of the date in "yyyy-MM-dd" format
     */
    public static String toDateOnly(Date date) {
        return DATE_FORMAT.format(date);
    }
}
