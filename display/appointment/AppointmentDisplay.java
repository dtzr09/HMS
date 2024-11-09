package display.appointment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.user.DoctorManager;
import display.ClearDisplay;
import display.EnterToGoBackDisplay;
import model.appointment.Appointment;
import model.appointment.enums.Weekdays;
import model.user.Doctor;
import model.user.Patient;
import utils.exceptions.PageBackException;
import utils.iocontrol.CustScanner;

public class AppointmentDisplay {
    private static final Map<Integer, String> timeSlotMap = new HashMap<>();

    static {
        timeSlotMap.put(1, "9:00 - 9:30");
        timeSlotMap.put(2, "9:30 - 10:00");
        timeSlotMap.put(3, "10:00 - 10:30");
        timeSlotMap.put(4, "10:30 - 11:00");
        timeSlotMap.put(5, "11:00 - 11:30");
        timeSlotMap.put(6, "11:30 - 12:00");
        timeSlotMap.put(7, "12:00 - 12:30");
        timeSlotMap.put(8, "12:30 - 13:00");
        timeSlotMap.put(9, "13:00 - 13:30");
        timeSlotMap.put(10, "13:30 - 14:00");
        timeSlotMap.put(11, "14:00 - 14:30");
        timeSlotMap.put(12, "14:30 - 15:00");
        timeSlotMap.put(13, "15:00 - 15:30");
        timeSlotMap.put(14, "15:30 - 16:00");
        timeSlotMap.put(15, "16:00 - 16:30");
        timeSlotMap.put(16, "16:30 - 17:00");
    }

    public static void appointmentRequestsDisplay(Doctor doctor) throws PageBackException {
        String fourColBorder = "+--------------------------------------+----------------------+-----------------+----------------------+";
        ClearDisplay.ClearConsole();
        System.out.println(fourColBorder);
        System.out.printf("| %-100s |%n", " " + "All Appointment Requests");
        System.out.println(fourColBorder);
        System.out.printf("| %-36s | %-20s | %-15s | %-20s|%n", "ID", "Date", "Time", "Patient ID");
        System.out.println(fourColBorder);

        try {
            List<Appointment> appointmentRequests = doctor.getAppointmentRequests();
            for (Appointment appointmentReq : appointmentRequests) {
                System.out.printf("| %-36s | %-20s | %-15s | %-20s |%n",
                        appointmentReq.getAppointmentID(), appointmentReq.getDateOfAppointment(),
                        appointmentReq.getTimeOfAppointment(), appointmentReq.getPatientID());
            }
        } catch (Exception e) {
            System.out.printf("| %-100s |%n", "No appointment requests found.");
        }
        System.out.println(fourColBorder);
        System.out.println();
        EnterToGoBackDisplay.display();
    }

    public static void upcomingAppointmentsDisplay(List<Appointment> upcomingAppointments) throws PageBackException {
        String fourColBorder = "+--------------------------------------+----------------------+-----------------+----------------------+";

        System.out.println(fourColBorder);
        System.out.printf("| %-100s |%n", " " + "Upcoming Appointments");
        System.out.println(fourColBorder);
        System.out.printf("| %-36s | %-20s | %-15s | %-20s|%n", "ID", "Date", "Time", "Patient ID");

        for (Appointment upcomingAppointment : upcomingAppointments) {
            System.out.printf("| %-36s | %-20s | %-15s | %-20s |%n",
                    upcomingAppointment.getAppointmentID(), upcomingAppointment.getDateOfAppointment(),
                    upcomingAppointment.getTimeOfAppointment(), upcomingAppointment.getPatientID());
        }

        System.out.println(fourColBorder);
        System.out.println();
        EnterToGoBackDisplay.display();
    }

    public static void viewScheduledAppointments(List<Appointment> appointments) throws PageBackException {
        String fourColBorder = "+--------------------------------------+----------------------+-----------------+----------------------+";

        System.out.println(fourColBorder);
        System.out.printf("| %-100s |%n", " " + "All Appointments");
        System.out.println(fourColBorder);
        System.out.printf("| %-36s | %-20s | %-15s | %-20s|%n", "ID", "Date", "Time", "Patient ID");

        for (Appointment appointment : appointments) {
            System.out.printf("| %-36s | %-20s | %-15s | %-20s |%n",
                    appointment.getAppointmentID(), appointment.getDateOfAppointment(),
                    appointment.getTimeOfAppointment(), appointment.getPatientID());
        }

        System.out.println(fourColBorder);
        System.out.println();
    }

    private static void timeSlotDisplay() {
        for (Map.Entry<Integer, String> entry : timeSlotMap.entrySet()) {
            System.out.printf("\t%d. %s%n", entry.getKey(), entry.getValue());
        }
        System.out.println();
    }

    private static void weekdayDisplay() {
        System.out.println("\t1. Monday");
        System.out.println("\t2. Tuesday");
        System.out.println("\t3. Wednesday");
        System.out.println("\t4. Thursday");
        System.out.println("\t5. Friday");
    }

    public static Map<String, List<String>> setAppointmentAvailability(Doctor doctor) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("Set Availability for Appointments");
        System.out.println("----------------------------------");

        Map<String, List<String>> appointmentAvailability = new HashMap<>();

        while (true) {
            weekdayDisplay();
            System.out.println();
            System.out.printf("Which day would you like to set availability for? ");
            int day = CustScanner.getIntChoice();

            System.out.println();
            timeSlotDisplay();
            System.out.println();
            System.out
                    .printf("Enter a list of time slots separated by commas (e.g. 1, 2, 3) base on the above menu. ");
            System.out.println();
            String timeSlots = CustScanner.getStrChoice();

            List<String> timeSlotList = new ArrayList<>();
            try {
                for (String slot : timeSlots.split(",")) {
                    timeSlotList.add(slot.trim());
                }
            } catch (Exception e) {
                System.out.printf("Invalid input. Please enter a list of integers separated by commas.");
            }
            appointmentAvailability.put(Integer.toString(day), timeSlotList);

            System.out.printf("Would you like to set availability for another day? (Y/N) ");
            if (CustScanner.getStrChoice().equalsIgnoreCase("n")) {
                break;
            }
        }

        return appointmentAvailability;
    }

    private static void updateAppointmentAvailabilityDisplay(Doctor doctor,
            Map<String, List<String>> oldAppointmentAvailability) throws PageBackException {
        System.out.println("Update Appointment Availability");
        System.out.println("-------------------------------");
        System.out.println();

        while (true) {
            weekdayDisplay();
            System.out.printf("Which day would you like to update availability for? ");
            int day = CustScanner.getIntChoice();

            timeSlotDisplay();
            System.out.println();
            System.out.printf("Enter a list of time slots separated by commas (e.g. 1, 2, 3) base on the above menu:");
            String timeSlots = CustScanner.getStrChoice();
            List<String> timeSlotList = new ArrayList<>();
            try {
                for (String slot : timeSlots.split(",")) {
                    timeSlotList.add(slot.trim());
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a list of integers separated by commas.");
            }

            oldAppointmentAvailability.put(Integer.toString(day), timeSlotList);

            System.out.println();
            System.out.println("Would you like to set availability for another day? (Y/N) ");
            if (CustScanner.getStrChoice().equalsIgnoreCase("n")) {
                break;
            }
        }

        if (oldAppointmentAvailability.isEmpty() || oldAppointmentAvailability == null) {
            System.out.println("No availability set. Press enter to go back.\n");
            if (CustScanner.getStrChoice().equals("")) {
                return;
            }
        }

        try {
            DoctorManager.setAppointmentAvailability(doctor, oldAppointmentAvailability);
        } catch (Exception e) {
            System.out.println("Error updating appointment availability.");
        }

        EnterToGoBackDisplay.display();
    }

    public static void displayDoctorTimeSlots(Doctor doctor) {
        Map<String, List<String>> appointmentAvailability = doctor.getAppointmentAvailability();
        for (Map.Entry<String, List<String>> entry : appointmentAvailability.entrySet()) {
            try {
                Weekdays day = null;
                switch (entry.getKey()) {
                    case "1" -> day = Weekdays.MONDAY;
                    case "2" -> day = Weekdays.TUESDAY;
                    case "3" -> day = Weekdays.WEDNESDAY;
                    case "4" -> day = Weekdays.THURSDAY;
                    case "5" -> day = Weekdays.FRIDAY;
                    default -> {
                        System.out.println("Something went wrong. Press enter to go back.");
                        if (CustScanner.getStrChoice().equals("")) {
                            throw new PageBackException();
                        }
                    }
                }

                System.out.printf("Day: %s\n", day.toCamelCase());
                List<String> timeSlotStrings = new ArrayList<>();
                for (String slot : entry.getValue()) {
                    int slotNumber = Integer.parseInt(slot.trim());
                    timeSlotStrings.add(timeSlotMap.get(slotNumber));
                }
                System.out.println("Appointment Slots:");
                for (String slot : timeSlotStrings) {
                    System.out.println("    " + slot);
                }
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void displayAppointmentAvailability(Doctor doctor,
            Map<String, List<String>> appointmentAvailability) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("Current Appointment Availability");
        System.out.println("-----------------------------------");
        System.out.println();

        for (Map.Entry<String, List<String>> entry : appointmentAvailability.entrySet()) {
            try {
                Weekdays day = null;
                switch (entry.getKey()) {
                    case "1" -> day = Weekdays.MONDAY;
                    case "2" -> day = Weekdays.TUESDAY;
                    case "3" -> day = Weekdays.WEDNESDAY;
                    case "4" -> day = Weekdays.THURSDAY;
                    case "5" -> day = Weekdays.FRIDAY;
                    default -> {
                        System.out.println("Something went wrong. Press enter to go back.");
                        if (CustScanner.getStrChoice().equals("")) {
                            throw new PageBackException();
                        }
                    }
                }

                System.out.printf("Day: %s\n", day.toCamelCase());
                List<String> timeSlotStrings = new ArrayList<>();
                for (String slot : entry.getValue()) {
                    int slotNumber = Integer.parseInt(slot.trim());
                    timeSlotStrings.add(timeSlotMap.get(slotNumber));
                }
                System.out.println("Appointment Slots:");
                for (String slot : timeSlotStrings) {
                    System.out.println("    " + slot);
                }
                System.out.println();
            } catch (Exception e) {
                EnterToGoBackDisplay.display();
            }
        }

        System.out.println();
        System.out.printf("Do you want to update your availability? (Y/N)");
        if (CustScanner.getStrChoice().equalsIgnoreCase("y")) {
            updateAppointmentAvailabilityDisplay(doctor, appointmentAvailability);
        }
        EnterToGoBackDisplay.display();
    }

    public static void displayPatientsAppointment(Patient patient) {
        String fiveColBorder = "+--------------------------------------+---------------------------+----------------------+-----------------+-----------------+";
        ClearDisplay.ClearConsole();
        System.out.println(fiveColBorder);
        System.out.printf("| %-90s |%n", " " + "Appointments");
        System.out.println(fiveColBorder);
        System.out.printf("| %-36s | %-25s | %-20s | %-15s | %-15s | %n", "ID", "Date", "Time", "Doctor Name",
                "Status");
        try {
            List<Appointment> appointments = patient.getAppointments();
            for (Appointment appointment : appointments) {
                Doctor doctor = DoctorManager.getDoctorByID(appointment.getDoctorID());
                System.out.printf("| %-36s | %-25s | %-15s | %-10s | %-20s | %n", appointment
                        .getAppointmentID(),
                        appointment.getDateOfAppointment(),
                        appointment.getTimeOfAppointment(),
                        doctor.getName(),
                        appointment.getAppointmentStatus());
            }
        } catch (Exception e) {
            System.out.printf("| %-90s %n", "No appointment found.");
        }
        System.out.println(fiveColBorder);
        System.out.println();
        System.out.println("--------------------------------------------");
    }

}
