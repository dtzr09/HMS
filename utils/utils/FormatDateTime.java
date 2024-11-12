package utils.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class FormatDateTime {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    public static String formatDateTimeToString(Date date) {
        SimpleDateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        return outputFormatter.format(date);
    }

    public static Date convertStringToDateTime(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        formatter.setLenient(false);

        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date convertDMYToTime(int date, int month, int year) {
        LocalDate fullDate = LocalDate.of(year, month, date);
        return java.sql.Date.valueOf(fullDate);
    }

    public static String toDateOnly(Date date) {
        // Convert the Date to a String in "yyyy-MM-dd" format
        String dateString = DATE_FORMAT.format(date);
        return dateString;
    }
}
