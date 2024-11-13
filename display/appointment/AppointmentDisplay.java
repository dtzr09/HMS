package display.appointment;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.appointment.AppointmentManager;
import controller.user.DoctorManager;
import controller.user.PatientManager;
import display.session.ClearDisplay;
import display.session.EnterToGoBackDisplay;
import display.user.PatientDisplay;
import model.appointment.Appointment;
import model.appointment.enums.AppointmentStatus;
import model.appointment.enums.Weekdays;
import model.user.Doctor;
import model.user.Patient;
import utils.exceptions.ModelAlreadyExistsException;
import utils.exceptions.ModelNotFoundException;
import utils.exceptions.PageBackException;
import utils.iocontrol.CustScanner;
import utils.utils.FormatDateTime;

/**
 * This class provides various methods for managing
 * and displaying appointment-related information for doctors and patients.
 * It includes functionality for approving or rejecting appointments, displaying
 * appointment requests, viewing scheduled appointments, setting availability,
 * and scheduling new appointments.
 */
public class AppointmentDisplay {
    // Mapping for time slots
    private static final Map<Integer, String> timeSlotMap = new HashMap<>();

    // Mapping for months
    private static final Map<Integer, String> monthMap = new HashMap<>();

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

    static {
        monthMap.put(1, "January");
        monthMap.put(2, "February");
        monthMap.put(3, "March");
        monthMap.put(4, "April");
        monthMap.put(5, "May");
        monthMap.put(6, "June");
        monthMap.put(7, "July");
        monthMap.put(8, "August");
        monthMap.put(9, "September");
        monthMap.put(10, "October");
        monthMap.put(11, "November");
        monthMap.put(12, "December");
    }

    /**
     * Retrieves the time slot associated with a given time slot ID.
     *
     * @param timeSlotID the ID representing the time slot
     * @return the time slot as a string, or {@code null} if no matching slot is
     *         found
     */
    public static String getTimeSlot(int timeSlotID) {
        return timeSlotMap.get(timeSlotID);
    }

    /**
     * Displays a menu for approving or rejecting an appointment.
     *
     * @param doctorID      the unique identifier for the doctor
     * @param appointmentID the unique identifier for the appointment
     * @throws PageBackException if the user chooses to go back
     */
    private static void approveOrRequestDisplay(String doctorID, String appointmentID) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("Manage Appointment Request");
        System.out.println("-------------------------");
        System.out.println();
        System.out.println("\t1. Approve");
        System.out.println("\t2. Reject");
        System.out.println("\t3. Go back");
        System.out.println();
        System.out.printf("What would you like to do? ");
        int choice = CustScanner.getIntChoice();
        System.out.println();
        switch (choice) {
            case 1:
                try {
                    AppointmentManager.approveAppointment(doctorID, appointmentID);
                    System.out.println("Appointment approved.");
                } catch (Exception e) {
                    System.out.println("Something went wrong while approving the appointment.");
                    EnterToGoBackDisplay.display();
                }
                break;
            case 2:
                try {
                    AppointmentManager.rejectAppointment(doctorID, appointmentID);
                    System.out.println("Appointment rejected.");
                } catch (Exception e) {
                    System.out.println("Something went wrong while rejecting the appointment.");
                    EnterToGoBackDisplay.display();
                }
                break;
            case 3:
                throw new PageBackException();
            default:
                System.out.println("Invalid input. Please try again.");
                EnterToGoBackDisplay.display();
        }

    }

    /**
     * Displays all appointment requests for a specified doctor.
     *
     * @param doctor the {@code Doctor} object representing the doctor
     * @throws PageBackException if the user chooses to go back
     */
    public static void appointmentRequestsDisplay(Doctor doctor) throws PageBackException {
        String fourColBorder = "+--------------------------------------+----------------------+-----------------+--------------------------------------+";
        ClearDisplay.ClearConsole();
        System.out.println(fourColBorder);
        System.out.printf("| %-116s |%n", " " + "All Appointment Requests");
        System.out.println(fourColBorder);
        System.out.printf("| %-36s | %-20s | %-15s | %-37s|%n", "ID", "Date", "Time", "Patient ID");
        System.out.println(fourColBorder);

        List<Appointment> appointmentRequests = AppointmentManager
                .getDoctorAppointmentsRequests(doctor.getModelID());
        if (appointmentRequests.isEmpty() || appointmentRequests == null) {
            System.out.printf("| %-100s |%n", "No appointment requests found.");
            System.out.println(fourColBorder);
            System.out.println();
            EnterToGoBackDisplay.display();
        }
        for (Appointment appointmentReq : appointmentRequests) {
            System.out.printf("| %-36s | %-20s | %-15s | %-20s |%n",
                    appointmentReq.getAppointmentID(),
                    appointmentReq.getDateOfAppointment(),
                    getTimeSlot(appointmentReq.getTimeOfAppointment()), appointmentReq.getPatientID());
        }
        System.out.println(fourColBorder);
        System.out.println();
        System.out.printf("Enter the appointment ID to approve or reject the appointment. ");
        String appointmentID = CustScanner.getStrChoice();
        approveOrRequestDisplay(doctor.getModelID(), appointmentID);

        System.out.println();
        EnterToGoBackDisplay.display();
    }

    /**
     * Displays all upcoming appointments for a doctor.
     *
     * @param upcomingAppointments a list of upcoming {@code Appointment} objects
     * @throws PageBackException if the user chooses to go back
     */
    public static void upcomingAppointmentsDisplay(List<Appointment> upcomingAppointments) throws PageBackException {
        String fourColBorder = "+--------------------------------------+----------------------+-----------------+--------------------------------------+";

        System.out.println(fourColBorder);
        System.out.printf("| %-110s |%n", " " + "Upcoming Appointments");
        System.out.println(fourColBorder);
        System.out.printf("| %-36s | %-20s | %-15s | %-37s|%n", "ID", "Date", "Time", "Patient ID");
        System.out.println(fourColBorder);

        for (Appointment upcomingAppointment : upcomingAppointments) {
            System.out.printf("| %-36s | %-20s | %-15s | %-36s |%n",
                    upcomingAppointment.getAppointmentID(),
                    upcomingAppointment.getDateOfAppointment(),
                    getTimeSlot(upcomingAppointment.getTimeOfAppointment()), upcomingAppointment.getPatientID());
        }

        System.out.println(fourColBorder);
        System.out.println();
        EnterToGoBackDisplay.display();
    }

    /**
     * Displays all appointments for a doctor.
     *
     * @param appointments a list of {@code Appointment} objects
     * @throws PageBackException if the user chooses to go back
     */
    public static void viewAppointments(List<Appointment> appointments) throws PageBackException {
        String fourColBorder = "+--------------------------------------+----------------------+-----------------+-------------------+";
        System.out.println(fourColBorder);
        System.out.printf("| %-97s |%n", " " + "All Appointments");
        System.out.println(fourColBorder);
        System.out.printf("| %-36s | %-20s | %-15s | %-18s|%n", "ID", "Date", "Time", "Patient");
        System.out.println(fourColBorder);
        for (Appointment appointment : appointments) {
            Patient patient = PatientManager.getPatientById(appointment.getPatientID());
            System.out.printf("| %-36s | %-20s | %-15s | %-17s |%n",
                    appointment.getAppointmentID(),
                    appointment.getDateOfAppointment(),
                    getTimeSlot(appointment.getTimeOfAppointment()), patient.getName());
        }
        System.out.println(fourColBorder);
        System.out.println();
    }

    /**
     * Displays the available time slots for appointments.
     */
    private static void timeSlotDisplay() {
        for (Map.Entry<Integer, String> entry : timeSlotMap.entrySet()) {
            System.out.printf("\t%d. %s%n", entry.getKey(), entry.getValue());
        }
        System.out.println();
    }

    /**
     * Displays the available weekdays for setting availability.
     */
    private static void weekdayDisplay() {
        System.out.println("\t1. Monday");
        System.out.println("\t2. Tuesday");
        System.out.println("\t3. Wednesday");
        System.out.println("\t4. Thursday");
        System.out.println("\t5. Friday");
    }

    /**
     * Displays a menu for setting the appointment availability of a doctor.
     *
     * @param doctor the {@code Doctor} object for whom availability is being set
     * @return a map of weekdays to time slot lists representing the availability
     * @throws PageBackException if the user chooses to go back
     */
    public static Map<String, List<String>> setAppointmentAvailabilityDisplay(Doctor doctor) throws PageBackException {
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

    /**
     * Updates the appointment availability of a doctor.
     *
     * @param doctor                     the {@code Doctor} object whose
     *                                   availability is being updated
     * @param oldAppointmentAvailability the existing availability map
     * @throws PageBackException if the user chooses to go back
     */
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

    /**
     * Displays the time slots available for a specified doctor.
     *
     * @param doctor the {@code Doctor} object for whom time slots are displayed
     * @throws PageBackException if the user chooses to go back
     */
    public static void displayDoctorTimeSlots(Doctor doctor) throws PageBackException {
        try {
            Map<String, List<String>> appointmentAvailability = DoctorManager.getAppointmentAvailability(doctor);
            if (appointmentAvailability.isEmpty() || appointmentAvailability == null) {
                System.out.println("The doctor has not set his availability.");
                EnterToGoBackDisplay.display();
            }
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
                    Map<String, String> timeSlots = new HashMap<>();
                    for (String slot : entry.getValue()) {
                        int slotNumber = Integer.parseInt(slot.trim());
                        timeSlots.put(Integer.toString(slotNumber), timeSlotMap.get(slotNumber));
                    }
                    System.out.println();
                    System.out.println("Appointment Slots:");
                    if (timeSlots.isEmpty()) {
                        System.out.println("The doctor has not set his availability.");
                        EnterToGoBackDisplay.display();
                    }
                    for (Map.Entry<String, String> slot : timeSlots.entrySet()) {
                        System.out.printf("\t%s: %s\n", slot.getKey(), slot.getValue());
                    }
                    System.out.println();
                } catch (Exception e) {
                    EnterToGoBackDisplay.display();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Displays the appointment availability for a doctor on a specific day.
     *
     * @param doctor the {@code Doctor} object for whom availability is displayed
     * @param day    the {@code DayOfWeek} representing the day
     * @throws PageBackException if the user chooses to go back
     */
    public static void displayAppointmentAvailabilityForADay(Doctor doctor, DayOfWeek day)
            throws PageBackException {
        Map<String, List<String>> appointmentAvailability = DoctorManager.getAppointmentAvailability(doctor);
        Map<Integer, List<String>> bookedSlots = AppointmentManager.getBookedAppointmentsOfDoctor(
                doctor.getModelID());
        List<String> bookedSlotsForDay = bookedSlots.get(day.getValue());
        Weekdays dayEnum = Weekdays.valueOf(day.toString());

        try {
            System.out.printf("Selected Day: %s\n", dayEnum.toCamelCase());
            System.out.println();
            System.out.println("Available Appointment Slots:");

            for (String slot : appointmentAvailability.get(Integer.toString(day.getValue()))) {
                int slotNumber = Integer.parseInt(slot.trim());
                if (bookedSlotsForDay != null && bookedSlotsForDay.contains(slot)) {
                    continue;
                }
                System.out.printf("\t%s: %s\n", Integer.toString(slotNumber), timeSlotMap.get(slotNumber));

            }

        } catch (Exception e) {
            System.out.println("No availability set for this day. Press choose another day.");
            displayAppointmentAvailabilityForADay(doctor, day);
        }
    }

    /**
     * Displays the entire appointment availability for a doctor.
     *
     * @param doctor                  the {@code Doctor} object for whom
     *                                availability is displayed
     * @param appointmentAvailability the map representing availability
     * @throws PageBackException if the user chooses to go back
     */
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

                Map<String, String> timeSlots = new HashMap<>();
                for (String slot : entry.getValue()) {
                    int slotNumber = Integer.parseInt(slot.trim());
                    timeSlots.put(Integer.toString(slotNumber), timeSlotMap.get(slotNumber));
                }
                System.out.println("Appointment Slots:");
                for (Map.Entry<String, String> slot : timeSlots.entrySet()) {
                    System.out.printf("\t%s: %s\n", slot.getKey(), slot.getValue());
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

    /**
     * Displays all appointments of a patient filtered by appointment status.
     *
     * @param patient the {@code Patient} object for whom appointments are displayed
     * @param status  the status to filter appointments (e.g., pending, scheduled)
     */
    public static void displayPatientsAppointment(Patient patient, AppointmentStatus status) {
        String fiveColBorder = "+--------------------------------------+---------------------------+----------------------+-----------------+-----------------+";
        System.out.println(fiveColBorder);
        System.out.printf("| %-123s |%n", " " + "Appointments");
        System.out.println(fiveColBorder);
        System.out.printf("| %-36s | %-25s | %-20s | %-15s | %-15s | %n", "ID", "Date", "Time", "Doctor Name",
                "Status");
        System.out.println(fiveColBorder);
        try {
            List<Appointment> appointments = AppointmentManager.getPatientAppointmentsByStatus(patient.getPatientID(),
                    status);
            if (appointments.isEmpty() || appointments == null) {
                System.out.printf("| %-90s %n", "No appointment found.");
            }
            for (Appointment appointment : appointments) {
                Doctor doctor = DoctorManager.getDoctorByID(appointment.getDoctorID());
                System.out.printf("| %-36s | %-25s | %-20s | %-15s | %-15s | %n", appointment
                        .getAppointmentID(),
                        appointment.getDateOfAppointment(),
                        getTimeSlot(appointment.getTimeOfAppointment()),
                        doctor.getName(),
                        appointment.getAppointmentStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("| %-90s %n", "No appointment found.");
        }
        System.out.println(fiveColBorder);
    }

    /**
     * Displays all appointments of a patient without filtering by status.
     *
     * @param patient the {@code Patient} object for whom appointments are displayed
     */
    public static void displayAllPatientsAppointment(Patient patient) {
        String fiveColBorder = "+--------------------------------------+---------------------------+----------------------+-----------------+-----------------+";
        System.out.println(fiveColBorder);
        System.out.printf("| %-123s |%n", " " + "Appointments");
        System.out.println(fiveColBorder);
        System.out.printf("| %-36s | %-25s | %-20s | %-15s | %-15s | %n", "ID", "Date", "Time", "Doctor Name",
                "Status");
        System.out.println(fiveColBorder);
        try {
            List<Appointment> appointments = AppointmentManager.getPatientAppointment(patient.getPatientID());
            if (appointments.isEmpty() || appointments == null) {
                System.out.printf("| %-90s %n", "No appointment found.");
            }
            for (Appointment appointment : appointments) {
                Doctor doctor = DoctorManager.getDoctorByID(appointment.getDoctorID());
                System.out.printf("| %-36s | %-25s | %-20s | %-15s | %-15s | %n", appointment
                        .getAppointmentID(),
                        appointment.getDateOfAppointment(),
                        getTimeSlot(appointment.getTimeOfAppointment()),
                        doctor.getName(),
                        appointment.getAppointmentStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("| %-90s %n", "No appointment found.");
        }
        System.out.println(fiveColBorder);
    }

    /**
     * Displays the scheduling menu for an appointment with a doctor.
     *
     * @param patient       the {@code Patient} object scheduling the appointment
     * @param doctorId      the unique identifier for the doctor
     * @param action        the action to perform (e.g., new appointment or
     *                      reschedule)
     * @param appointmentID the unique identifier for the appointment (if
     *                      rescheduling)
     * @throws PageBackException           if the user chooses to go back
     * @throws ModelNotFoundException      if the doctor or appointment is not found
     * @throws ModelAlreadyExistsException if there is a scheduling conflict
     */
    public static void scheduleAppointmentDisplay(Patient patient, String doctorId, String action, String appointmentID)
            throws PageBackException, ModelNotFoundException, ModelAlreadyExistsException {
        Doctor doctor = null;
        doctor = DoctorManager.getDoctorByID(doctorId);
        Map<String, List<String>> currentAvailability = DoctorManager.getAppointmentAvailability(doctor);
        if (currentAvailability.isEmpty() || currentAvailability == null) {
            System.out.println();
            System.out.println("The doctor has not set his availability.");
            System.out.println();
            EnterToGoBackDisplay.display();
        }
        System.out.printf("Enter the month (1 for Jan, etc) that you would like to make an appointment for: ");
        int month = CustScanner.getIntChoice();
        if (month < 1 || month > 12) {
            System.out.println("Invalid month. \n");
            System.out.println("Press enter to retry or type b to go back to main menu.");
            if (CustScanner.getStrChoice().equalsIgnoreCase("b")) {
                PatientDisplay.patientDisplay(doctor);
            } else {
                throw new PageBackException();
            }
        }

        scheduleAppointment(patient.getPatientID(), doctor, month, action, appointmentID);
    }

    /**
     * Displays the appointment availability for a patient viewing a doctor's
     * schedule.
     *
     * @param doctor the {@code Doctor} object whose availability is being viewed
     * @throws PageBackException if the user chooses to go back
     */
    public static void displayAppointmentAvailabilityForPatient(Doctor doctor) throws PageBackException {
        Map<String, List<String>> currentAvailability = DoctorManager.getAppointmentAvailability(doctor);
        Map<Integer, List<String>> bookedSlots = AppointmentManager.getBookedAppointmentsOfDoctor(
                doctor.getModelID());

        for (Map.Entry<String, List<String>> entry : currentAvailability.entrySet()) {
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
                System.out.println();
                System.out.println("Available Appointment Slots:");
                for (String slot : entry.getValue()) {
                    int slotNumber = Integer.parseInt(slot.trim());
                    if (bookedSlots.containsKey(Integer.parseInt(entry.getKey()))
                            && bookedSlots.get(Integer.parseInt(entry.getKey())).contains(slot)) {
                        continue;
                    }
                    System.out.printf("\t%s: %s\n", Integer.toString(slotNumber), timeSlotMap.get(slotNumber));
                }
                System.out.println();
            } catch (Exception e) {
                EnterToGoBackDisplay.display();
            }
        }
    }

    /**
     * Schedules an appointment for a patient with a doctor on a specific date and
     * time slot.
     *
     * @param patientID     the unique identifier for the patient
     * @param doctor        the {@code Doctor} object with whom the appointment is
     *                      being scheduled
     * @param month         the month of the appointment
     * @param action        the action to perform (e.g., new appointment or
     *                      reschedule)
     * @param appointmentID the unique identifier for the appointment (if
     *                      rescheduling)
     * @throws PageBackException           if the user chooses to go back
     * @throws ModelNotFoundException      if the doctor or appointment is not found
     * @throws ModelAlreadyExistsException if there is a scheduling conflict
     */
    private static void scheduleAppointment(String patientID, Doctor doctor, int month, String action,
            String appointmentID)
            throws PageBackException, ModelNotFoundException, ModelAlreadyExistsException {
        ClearDisplay.ClearConsole();
        System.out.println("Schedule an Appointment");
        System.out.println("----------------------------");
        System.out.println();
        System.out.println("This is the doctor's availability for the month.");

        System.out.println();
        displayAppointmentAvailabilityForPatient(doctor);
        System.out.println();
        System.out.println("--------------------------------");
        System.out.println();

        int year = 2024;
        CalendarDisplay.calendarView(year, month);
        System.out.println();

        System.out.printf("Enter the date of the month that you would like to make an appointment for: ");
        int date = CustScanner.getIntChoice();
        if (date < 1 || date > 31) {
            System.out.println("Invalid date. \n");
            System.out.println("Press enter to retry or type b to go back to main menu.");
            if (CustScanner.getStrChoice().equalsIgnoreCase("b")) {
                PatientDisplay.patientDisplay(doctor);
            } else {
                throw new PageBackException();
            }
        }

        System.out.println();
        LocalDate fullDate = LocalDate.of(year, month, date);
        DayOfWeek day = fullDate.getDayOfWeek();
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            System.out.println(
                    "The doctor is not available on weekends. Please choose another day.");
            // if (CustScanner.getStrChoice().equals(""))
            // scheduleAppointment(patientID, doctor, month, action, appointmentID);
            throw new PageBackException();
        }

        if (!AppointmentManager.isTimeSlotAvailable(doctor, day)) {
            System.out.printf("No available time slots for this day. Please try again. ");
            // if (CustScanner.getStrChoice().equals(""))
            // scheduleAppointment(patientID, doctor, month, action, appointmentID);
            throw new PageBackException();
        }

        displayAppointmentAvailabilityForADay(doctor, day);
        Date appointmentDate = FormatDateTime.convertDMYToTime(date, month, year);
        String appointmentDateStr = FormatDateTime.formatDate(appointmentDate);

        System.out.println();
        System.out.println();
        System.out.printf("Enter the time slot that you would like to make an appointment for: ");
        int timeSlotID = CustScanner.getIntChoice();

        try {
            if (action == "reschedule") {
                AppointmentManager.rescheduleAppointment(patientID, appointmentID, timeSlotID,
                        appointmentDateStr);
            } else {
                AppointmentManager.scheduleNewAppointment(patientID, doctor.getModelID(),
                        timeSlotID, appointmentDateStr);
            }
            System.out.println("Appointment scheduled.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong.");
        }
    }
}
