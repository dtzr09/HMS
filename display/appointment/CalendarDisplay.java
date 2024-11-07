package display.appointment;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

import utils.iocontrol.CustScanner;

public class CalendarDisplay {
    public static void calendarDisplay() {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();
        calendarView(year, month);
        System.out.println(
                "Press 'n' to view the next month or 'p' to view the previous month. Else press any key to exit.");
        while (true) {
            System.out.println(
                    "Press 'n' to view the next month or 'p' to view the previous month. Else press any key to exit.");
            String input = CustScanner.getStrChoice();
            if (input.equalsIgnoreCase("n")) {
                month++;
                if (month > 12) {
                    month = 1;
                    year++;
                }
            } else if (input.equalsIgnoreCase("p")) {
                month--;
                if (month < 1) {
                    month = 12;
                    year--;
                }
            } else {
                break;
            }

            calendarView(year, month);
            input = CustScanner.getStrChoice();
        }

        System.out.println("Exiting calendar display...");
        System.exit(0);

    }

    private static void calendarView(int year, int month) {
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
