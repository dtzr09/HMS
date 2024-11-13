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

public class AppointmentManager {

    /**
     * Check if an appointment exists
     * 
     * @param appointmentID
     * @return Boolean
     */
    public static Boolean doesAppointmentExist(String appointmentID) {
        try {
            return AppointmentDatabase.getDB().getByID(appointmentID) != null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get an appointment by patient and appointment ID
     * 
     * @param patientID
     * @param appointmentID
     * @return Appointment
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
     * Get an appointment by appointment ID
     * 
     * @param appointmentID
     * @return Appointment
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
     * Get all the appointments of a patient
     * 
     * @param patientID
     * @return List<Appointment>
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
     * Get all the appointments of a patient by status
     * 
     * @param patientID
     * @param status
     * @return List<Appointment>
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
     * Create a new appointment
     * 
     * @param appointment
     * @throws ModelAlreadyExistsException
     */
    public static void createAppointment(Appointment appointment) throws ModelAlreadyExistsException {
        AppointmentDatabase.getDB().add(appointment);
    }

    public static void scheduleNewAppointment(String patientID, String doctorID, int timeSlotID,
            String appointmentDate) throws ModelAlreadyExistsException {
        String appointmentID = UUID.randomUUID().toString();
        Appointment newAppointment = new Appointment(appointmentID, AppointmentStatus.PENDING, patientID,
                appointmentDate,
                timeSlotID, doctorID);
        createAppointment(newAppointment);
    }

    /**
     * Get all the appointments
     * 
     * @return List<Appointment>
     */
    public static List<Appointment> getAllAppointments() {
        return AppointmentDatabase.getDB().getAllAppointments();
    }

    /**
     * Get all the appointments of a doctor
     * 
     * @param doctorID
     * @return List<Appointment>
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
     * Get all the scheduled appointments of a doctor
     * 
     * @param doctorID
     * @return List<Appointment>
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
     * Update an appointment
     * 
     * @param newAppointment
     * @throws ModelNotFoundException
     */
    public static void updateAppointment(Appointment newAppointment) throws ModelNotFoundException {
        AppointmentDatabase.getDB().update(newAppointment);
    }

    /**
     * Cancel an appointment
     * 
     * @param patientID
     * @param appointmentID
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
     * Reschedule an appointment
     * 
     * @param patientID
     * @param appointmentID
     * @param timeSlotID
     * @param newDate
     * @throws ModelNotFoundException
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
     * Get an appointment by doctor and appointment ID
     * 
     * @param doctorID
     * @param appointmentID
     * @return
     * @throws ModelNotFoundException
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
     * Approve an appointment for a doctor and create an appointment outcome
     * 
     * @param doctorID
     * @param appointmentID
     * @throws ModelNotFoundException
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
     * Reject an appointment for a doctor
     * 
     * @param doctorID
     * @param appointmentID
     * @throws ModelNotFoundException
     */
    public static void rejectAppointment(String doctorID, String appointmentID) throws ModelNotFoundException {
        Appointment appointment = getAppointmentByDoctorAndID(doctorID, appointmentID);
        appointment.setAppointmentStatus(AppointmentStatus.REJECTED);
        updateAppointment(appointment);
    }

    /**
     * Get all the appointments of a doctor
     * 
     * @param doctorID
     * @return List<Appointment>
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
     * Get all the appointments of a doctor that has been booked
     * 
     * @param doctorID
     * @return Map<Integer, List<String>>
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
     * Get all the appointments of a doctor with appointment outcome status pending
     * 
     * @param appointments
     * @param doctorID
     * @return ArrayList<Appointment>
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
     * Checks if a time slot is available for a doctor
     * 
     * @param doctorID
     * @param day
     * @return Boolean
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
