package display.appointment;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

public class CalendarDisplay {
    /**
     * Displays the calendar for the specified month and year
     * 
     * @param year
     * @param month
     */
    public static void calendarView(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        int daysInMonth = yearMonth.lengthOfMonth();

        System.out.printf("%n%s %d%n", firstDayOfMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH), year);
        System.out.println("Sun Mon Tue Wed Thu Fri Sat");

        int startDay = firstDayOfMonth.getDayOfWeek().getValue() % 7;

        for (int i = 0; i < startDay; i++) {
            System.out.print("    ");
        }

        for (int day = 1; day <= daysInMonth; day++) {
            System.out.printf("%3d ", day);

            if ((day + startDay) % 7 == 0) {
                System.out.println();
            }
        }

        System.out.println();
    }
}
