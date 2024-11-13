package controller.appointment;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import controller.user.DoctorManager;
import database.appointment.AppointmentDatabase;
import model.appointment.Appointment;
import model.appointment.AppointmentOutcome;
import model.appointment.enums.AppointmentOutcomeStatus;
import model.appointment.enums.AppointmentStatus;
import model.user.Doctor;
import utils.exceptions.ModelAlreadyExistsException;
import utils.exceptions.ModelNotFoundException;
/**
 * The AppointmentManager class provides utility methods for scheduling Appointments.
 */

public class AppointmentManager {

    /**
     * Checks if an appointment with the specified ID exists in the appointment database.
     * 
     * This method attempts to retrieve an appointment by its ID from the appointment database.
     * If the appointment is found, it returns {@code true}; otherwise, it returns {@code false}.
     * Any exceptions during the lookup are caught and result in a {@code false} return value.
     * 
     * @param appointmentID The ID of the appointment to check.
     * @return {@code true} if an appointment with the specified ID exists; {@code false} otherwise.
     */
    public static Boolean doesAppointmentExist(String appointmentID) {
        try {
            return AppointmentDatabase.getDB().getByID(appointmentID) != null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Retrieves an appointment by patient ID and appointment ID.
     * 
     * This method searches through all available appointments to find a match for the
     * specified patient ID and appointment ID. If a matching appointment is found, 
     * it is returned; otherwise, a message is printed and {@code null} is returned.
     * 
     * @param patientID     The ID of the patient associated with the appointment.
     * @param appointmentID The ID of the appointment to retrieve.
     * @return The matching {@link Appointment} object if found; {@code null} if no match is found.
     */
    public static Appointment getAppointmentByPatientAndID(String patientID, String appointmentID) {
        try {
            List<Appointment> appointments = getAllAppointments();
            for (Appointment appointment : appointments) {
                if (appointment.getPatientID().equals(patientID)
                        && appointment.getAppointmentID().equals(appointmentID))
                    return appointment;
            }
        } catch (Exception e) {
            System.out.println("Appointment not found.");
        }
        return null;
    }

    /**
     * Retrieves an appointment by its ID from the appointment database.
     * 
     * This method attempts to fetch an appointment using the provided appointment ID
     * from the appointment database. If the appointment is found, it is returned; 
     * otherwise, an error message is printed and {@code null} is returned.
     * 
     * @param appointmentID The ID of the appointment to retrieve.
     * @return The {@link Appointment} object if found; {@code null} if no matching appointment is found.
     */

    public static Appointment getAppointmentByID(String appointmentID) {
        try {
            return AppointmentDatabase.getDB().getByID(appointmentID);
        } catch (Exception e) {
            System.out.println("Appointment not found.");
        }
        return null;
    }

    /**
     * Retrieves all appointments associated with a specific patient ID.
     * 
     * This method iterates through all available appointments and collects those
     * that match the specified patient ID. The resulting list of appointments is 
     * returned.
     * 
     * @param patientID The ID of the patient whose appointments are to be retrieved.
     * @return A list of {@link Appointment} objects associated with the specified patient.
     *         If no appointments are found, an empty list is returned.
     */
    public static List<Appointment> getPatientAppointment(String patientID) {
        List<Appointment> patientAppointments = new ArrayList<>();
        for (Appointment appointment : getAllAppointments()) {
            if (appointment.getPatientID().equals(patientID))
                patientAppointments.add(appointment);
        }
        return patientAppointments;
    }

    /**
     * Retrieves all appointments for a specific patient with a specified status.
     * 
     * This method first obtains all appointments associated with a given patient ID.
     * It then filters these appointments based on their status, returning only those
     * that match the provided {@link AppointmentStatus}.
     * 
     * @param patientID The ID of the patient whose appointments are to be retrieved.
     * @param status    The status of the appointments to filter by (e.g., scheduled, completed).
     * @return A list of {@link Appointment} objects that match the specified status.
     *         If no matching appointments are found, an empty list is returned.
     */
    public static List<Appointment> getPatientAppointmentsByStatus(String patientID, AppointmentStatus status) {
        List<Appointment> allpatientAppointments = getPatientAppointment(patientID);
        ArrayList<Appointment> scheduledPatientAppointment = new ArrayList<Appointment>();
        if (allpatientAppointments == null || allpatientAppointments.isEmpty())
            return scheduledPatientAppointment;
        for (Appointment appointment : allpatientAppointments) {
            if (appointment.getAppointmentStatus().equals(status))
                scheduledPatientAppointment.add(appointment);
        }
        return scheduledPatientAppointment;
    }

    /**
     * Adds a new appointment to the appointment database.
     * 
     * This method attempts to add a provided {@link Appointment} object to the
     * appointment database. If an appointment with the same ID already exists,
     * a {@link ModelAlreadyExistsException} is thrown.
     * 
     * @param appointment The {@link Appointment} object to be added to the database.
     * @throws ModelAlreadyExistsException If an appointment with the same ID already exists in the database.
     */
    public static void createAppointment(Appointment appointment) throws ModelAlreadyExistsException {
        AppointmentDatabase.getDB().add(appointment);
    }
    /**
     * Creates and schedules a new appointment for a specified patient and doctor.
     * 
     * This method generates a new appointment ID and creates a new {@link Appointment}
     * object with the provided patient ID, doctor ID, time slot ID, and appointment date.
     * The new appointment is added to the appointment database. If an appointment with
     * the same ID already exists, a {@link ModelAlreadyExistsException} is thrown.
     * 
     * @param patientID       The ID of the patient for the new appointment.
     * @param doctorID        The ID of the doctor for the new appointment.
     * @param timeSlotID      The time slot ID for the appointment.
     * @param appointmentDate The date of the appointment.
     * @throws ModelAlreadyExistsException If an appointment with the same ID already exists in the database.
     */
    public static void scheduleNewAppointment(String patientID, String doctorID, int timeSlotID,
            String appointmentDate) throws ModelAlreadyExistsException {
        String appointmentID = UUID.randomUUID().toString();
        Appointment newAppointment = new Appointment(appointmentID, AppointmentStatus.PENDING, patientID,
                appointmentDate,
                timeSlotID, doctorID);
        createAppointment(newAppointment);
    }

    /**
     * Retrieves all appointments from the appointment database.
     * 
     * This method fetches and returns a list of all {@link Appointment} objects
     * stored in the appointment database.
     * 
     * @return A list of all {@link Appointment} objects in the database.
     */
    public static List<Appointment> getAllAppointments() {
        return AppointmentDatabase.getDB().getAllAppointments();
    }

    /**
     * Retrieves pending appointment requests for a specific doctor.
     * 
     * This method fetches all appointments from the database and filters them
     * to return a list of appointments associated with the specified doctor ID 
     * that have a status of {@link AppointmentStatus#PENDING}.
     * 
     * @param doctorID The ID of the doctor whose pending appointment requests are to be retrieved.
     * @return A list of {@link Appointment} objects that match the specified doctor ID
     *         and have a status of pending. If no such appointments are found, an empty list is returned.
     */
    public static List<Appointment> getDoctorAppointmentsRequests(String doctorID) {
        ArrayList<Appointment> doctorAppointments = new ArrayList<Appointment>();
        List<Appointment> appointments = getAllAppointments();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorID().equals(doctorID)
                    && appointment.getAppointmentStatus().equals(AppointmentStatus.PENDING))
                doctorAppointments.add(appointment);
        }
        return doctorAppointments;
    }

    /**
     * Retrieves approved appointments for a specific doctor.
     * 
     * This method fetches all appointments from the database and filters them
     * to return a list of appointments associated with the specified doctor ID
     * that have a status of {@link AppointmentStatus#APPROVED}.
     * 
     * @param doctorID The ID of the doctor whose approved appointments are to be retrieved.
     * @return A list of {@link Appointment} objects that match the specified doctor ID
     *         and have a status of approved. If no such appointments are found, an empty list is returned.
     */
    public static ArrayList<Appointment> getScheduledDoctorAppointments(String doctorID) {
        ArrayList<Appointment> doctorAppointments = new ArrayList<Appointment>();
        List<Appointment> appointments = getAllAppointments();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorID().equals(doctorID)
                    && appointment.getAppointmentStatus().equals(AppointmentStatus.APPROVED))
                doctorAppointments.add(appointment);
        }
        return doctorAppointments;
    }

    /**
     * Updates an existing appointment in the appointment database.
     * 
     * This method updates an appointment in the database with the provided
     * {@link Appointment} object. If the appointment does not exist in the database,
     * a {@link ModelNotFoundException} is thrown.
     * 
     * @param newAppointment The {@link Appointment} object containing the updated details.
     * @throws ModelNotFoundException If the appointment to be updated cannot be found in the database.
     */
    public static void updateAppointment(Appointment newAppointment) throws ModelNotFoundException {
        AppointmentDatabase.getDB().update(newAppointment);
    }

    /**
     * Cancels an existing appointment for a specified patient.
     * 
     * This method attempts to find the appointment associated with the provided
     * patient ID and appointment ID. If found, it updates the appointment's
     * status to {@link AppointmentStatus#CANCELLED} and saves the changes. 
     * If the appointment cannot be found, an error message is displayed.
     * 
     * @param patientID    The ID of the patient whose appointment is to be cancelled.
     * @param appointmentID The ID of the appointment to be cancelled.
     */
    public static void cancelAppointment(String patientID, String appointmentID) {
        try {
            Appointment appointment = getAppointmentByPatientAndID(patientID, appointmentID);
            appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
            updateAppointment(appointment);
        } catch (Exception e) {
            System.out.println("Appointment not found.");
        }
    }

    /**
     * Reschedules an existing appointment for a specified patient.
     * 
     * This method attempts to find the appointment associated with the provided
     * patient ID and appointment ID. If found, it updates the appointment's 
     * date and time to the new values, sets its status to {@link AppointmentStatus#PENDING}, 
     * and saves the changes. If the appointment cannot be found, a {@link ModelNotFoundException} 
     * is thrown.
     * 
     * @param patientID      The ID of the patient whose appointment is to be rescheduled.
     * @param appointmentID  The ID of the appointment to be rescheduled.
     * @param timeSlotID     The new time slot ID for the rescheduled appointment.
     * @param newDate        The new date for the rescheduled appointment.
     * @throws ModelNotFoundException If the appointment to be rescheduled cannot be found.
     */
    public static void rescheduleAppointment(String patientID, String appointmentID, int timeSlotID, String newDate)
            throws ModelNotFoundException {
        try {
            Appointment appointment = getAppointmentByPatientAndID(patientID, appointmentID);
            appointment.setDateOfAppointment(newDate);
            appointment.setTimeOfAppointment(timeSlotID);
            appointment.setAppointmentStatus(AppointmentStatus.PENDING);
            updateAppointment(appointment);
        } catch (Exception e) {
            throw new ModelNotFoundException("Appointment not found.");
        }
    }

    /**
     * Retrieves an appointment associated with a specific doctor by appointment ID.
     * 
     * This method searches through all appointments to find one that matches the
     * provided doctor ID and appointment ID. If a matching appointment is found,
     * it is returned; otherwise, a {@link ModelNotFoundException} is thrown.
     * 
     * @param doctorID      The ID of the doctor whose appointment is to be retrieved.
     * @param appointmentID The ID of the appointment to retrieve.
     * @return The {@link Appointment} object if found, or throws a {@link ModelNotFoundException} if not found.
     * @throws ModelNotFoundException If the appointment cannot be found for the given doctor and appointment ID.
     */
    private static Appointment getAppointmentByDoctorAndID(String doctorID, String appointmentID)
            throws ModelNotFoundException {
        List<Appointment> appointments = getAllAppointments();
        Appointment appointment = null;
        for (Appointment apt : appointments) {
            if (apt.getDoctorID().equals(doctorID)
                    && apt.getAppointmentID().equals(appointmentID))
                appointment = apt;
        }
        return appointment;
    }

    /**
     * Approves an appointment for a specific doctor and updates its status to approved.
     * 
     * This method retrieves the appointment associated with the provided doctor and appointment ID. 
     * If the appointment is found, it updates the appointment's status to {@link AppointmentStatus#APPROVED}. 
     * Additionally, a new {@link AppointmentOutcome} is created with a status of {@link AppointmentOutcomeStatus#PENDING} 
     * and saved. The appointment and its outcome are then updated in the system.
     * 
     * @param doctorID      The ID of the doctor approving the appointment.
     * @param appointmentID The ID of the appointment to be approved.
     * @throws ModelNotFoundException If the appointment cannot be found for the given doctor and appointment ID.
     */
    public static void approveAppointment(String doctorID, String appointmentID) throws ModelNotFoundException {
        Appointment appointment = getAppointmentByDoctorAndID(doctorID, appointmentID);
        appointment.setAppointmentStatus(AppointmentStatus.APPROVED);
        String appointmentOutcomeID = UUID.randomUUID().toString();
        AppointmentOutcome appointmentOutcome = new AppointmentOutcome(appointmentOutcomeID,
                appointment.getPatientID(), doctorID, appointmentID, AppointmentOutcomeStatus.PENDING);

        updateAppointment(appointment);
        AppointmentOutcomeManager.createNewAppointmentOutcome(appointmentOutcome);
    }

    /**
     * Rejects an appointment for a specific doctor and updates its status to rejected.
     * 
     * This method retrieves the appointment associated with the provided doctor and appointment ID. 
     * If the appointment is found, its status is updated to {@link AppointmentStatus#REJECTED}.
     * The updated appointment is then saved to the system.
     * 
     * @param doctorID      The ID of the doctor rejecting the appointment.
     * @param appointmentID The ID of the appointment to be rejected.
     * @throws ModelNotFoundException If the appointment cannot be found for the given doctor and appointment ID.
     */
    public static void rejectAppointment(String doctorID, String appointmentID) throws ModelNotFoundException {
        Appointment appointment = getAppointmentByDoctorAndID(doctorID, appointmentID);
        appointment.setAppointmentStatus(AppointmentStatus.REJECTED);
        updateAppointment(appointment);
    }

    /**
     * Retrieves all appointments for a specific doctor.
     * 
     * This method filters and returns a list of all appointments that are associated with the given doctor ID. 
     * It searches through all the available appointments and collects those whose `doctorID` matches the provided value.
     * 
     * @param doctorID The ID of the doctor whose appointments are to be retrieved.
     * @return A list of {@link Appointment} objects for the specified doctor. If no appointments are found, an empty list is returned.
     */
    public static ArrayList<Appointment> getAllDoctorAppointments(String doctorID) {
        ArrayList<Appointment> doctorAppointments = new ArrayList<>();
        List<Appointment> appointments = getAllAppointments();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorID().equals(doctorID))
                doctorAppointments.add(appointment);
        }
        return doctorAppointments;
    }

    /**
     * Retrieves the booked appointments of a specific doctor grouped by day of the week.
     * 
     * This method iterates through all the appointments of the specified doctor and filters them based on their 
     * status (either PENDING or APPROVED). It groups the appointments by their time slots and day of the week 
     * (from Monday to Sunday), returning a map that associates each day of the week with a list of appointment 
     * time slots for that day.
     * 
     * @param doctorID The ID of the doctor whose booked appointments are to be retrieved.
     * @return A map where the key is the day of the week (1 for Monday, 7 for Sunday), and the value is a 
     *         list of time slots (represented as integers) for the doctor on that day.
     */
    public static Map<Integer, List<String>> getBookedAppointmentsOfDoctor(String doctorID) {
        Map<Integer, List<String>> doctorAppointments = new HashMap<>();
        List<Appointment> appointments = getAllDoctorAppointments(doctorID);
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentStatus().equals(AppointmentStatus.PENDING)
                    || appointment.getAppointmentStatus().equals(AppointmentStatus.APPROVED)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date = LocalDate.parse(appointment.getDateOfAppointment(), formatter);
                int dayOfWeekValue = date.getDayOfWeek().getValue();
                if (doctorAppointments.containsKey(appointment.getTimeOfAppointment())) {
                    doctorAppointments.get(appointment.getTimeOfAppointment())
                            .add(Integer.toString(appointment.getTimeOfAppointment()));
                } else {
                    List<String> appointmentsList = new ArrayList<>();
                    appointmentsList.add(Integer.toString(appointment.getTimeOfAppointment()));
                    doctorAppointments.put(dayOfWeekValue, appointmentsList);
                }
            }
        }
        return doctorAppointments;
    }

    /**
     * Retrieves appointments with incomplete outcomes for a specific doctor.
     * 
     * This method checks all appointments and compares them with the outcomes associated with the specified 
     * doctor. It filters out appointments that have outcomes marked as PENDING, indicating that the outcomes 
     * have not yet been completed. It returns a list of appointments that are awaiting outcomes.
     * 
     * @param appointments The list of appointments to be checked for incomplete outcomes.
     * @param doctorID The ID of the doctor whose appointments are to be checked.
     * @return A list of appointments that have an incomplete (PENDING) outcome for the specified doctor.
     */
    public static ArrayList<Appointment> getAppointmentWithIncompleteOutcome(List<Appointment> appointments,
            String doctorID) {
        ArrayList<Appointment> pendingAppointments = new ArrayList<>();
        List<AppointmentOutcome> appointmentOutcomes = AppointmentOutcomeManager
                .getAppointmentOutcomeByDoctorID(doctorID);
        for (AppointmentOutcome outcome : appointmentOutcomes) {
            for (Appointment appointment : appointments) {
                if (outcome.getAppointmentID().equals(appointment.getAppointmentID())
                        && outcome.getStatus().equals(AppointmentOutcomeStatus.PENDING)) {
                    pendingAppointments.add(appointment);
                }
            }
        }
        return pendingAppointments;
    }

    /**
     * Checks if a doctor has available time slots on a specific day.
     * 
     * This method checks the doctor's appointment availability for a given day of the week. It retrieves the 
     * doctor's appointment availability and checks if there are any time slots scheduled for the specified 
     * day. If availability is found, it returns true; otherwise, false.
     * 
     * @param doctor The doctor whose availability is being checked.
     * @param day The day of the week to check for availability.
     * @return A boolean value indicating whether the doctor has available time slots on the given day.
     */
    public static Boolean isTimeSlotAvailable(Doctor doctor, DayOfWeek day) {
        int dayValue = day.getValue();
        Map<String, List<String>> appointmentAvailability = DoctorManager.getAppointmentAvailability(doctor);
        if (appointmentAvailability.containsKey(Integer.toString(dayValue))) {
            return true;
        }
        return false;
    }
}
