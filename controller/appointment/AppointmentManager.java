package controller.appointment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import controller.user.PatientManager;
import database.appointment.AppointmentDatabase;
import model.appointment.Appointment;
import model.appointment.enums.AppointmentStatus;
import model.user.Patient;
import utils.exceptions.ModelAlreadyExistsException;
import utils.exceptions.ModelNotFoundException;
import utils.utils.FormatDateTime;

public class AppointmentManager {

    public static Boolean doesAppointmentExist(String appointmentID) {
        try {
            return AppointmentDatabase.getDB().getByID(appointmentID) != null;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Appointment> getAppointmentsOfPatient(String patientID) {
        List<Appointment> appointments = new ArrayList<Appointment>();
        Patient patient = PatientManager.getPatientById(patientID);
        try {
            appointments = patient.getAppointments();
        } catch (Exception e) {
            System.out.println("No appointments found for this patient.");
        }

        return appointments;
    }

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

    public static Appointment getAppointmentByID(String appointmentID) {
        try {
            return AppointmentDatabase.getDB().getByID(appointmentID);
        } catch (Exception e) {
            System.out.println("Appointment not found.");
        }
        return null;
    }

    public static List<Appointment> getPatientAppointment(String patientID) {
        List<Appointment> patientAppointments = new ArrayList<>();
        for (Appointment appointment : getAllAppointments()) {
            if (appointment.getPatientID().equals(patientID))
                patientAppointments.add(appointment);
        }
        return patientAppointments;
    }

    public static Boolean isAppointmentAvailable(String patientID, String appointmentID, String appointmentDate,
            int timeSlotID) {
        try {
            List<Appointment> appointments = getPatientAppointment(patientID);
            if (appointments == null || appointments.isEmpty())
                return true;
            for (Appointment appointment : appointments) {
                if (appointment.getDateOfAppointment().equals(appointmentDate)
                        && appointment.getTimeOfAppointment() == timeSlotID) {

                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

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

    public static List<Appointment> getAllAppointments() {
        return AppointmentDatabase.getDB().getAllAppointments();
    }

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

    public static List<Appointment> getDoctorAppointments(String doctorID) {
        ArrayList<Appointment> doctorAppointments = new ArrayList<Appointment>();
        List<Appointment> appointments = getAllAppointments();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorID().equals(doctorID)
                    && appointment.getAppointmentStatus().equals(AppointmentStatus.APPROVED))
                doctorAppointments.add(appointment);
        }
        return doctorAppointments;
    }

    public static void updateAppointment(Appointment newAppointment) throws ModelNotFoundException {
        AppointmentDatabase.getDB().update(newAppointment);
    }

    public static void cancelAppointment(String patientID, String appointmentID) {
        try {
            Appointment appointment = getAppointmentByPatientAndID(patientID, appointmentID);
            appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
            updateAppointment(appointment);
        } catch (Exception e) {
            System.out.println("Appointment not found.");
        }
    }

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

    public static void approveAppointment(String doctorID, String appointmentID) throws ModelNotFoundException {
        try {
            Appointment appointment = getAppointmentByDoctorAndID(doctorID, appointmentID);
            appointment.setAppointmentStatus(AppointmentStatus.APPROVED);
            updateAppointment(appointment);
        } catch (Exception e) {
            throw new ModelNotFoundException("Appointment not found.");
        }
    }

    public static void rejectAppointment(String doctorID, String appointmentID) throws ModelNotFoundException {
        try {
            Appointment appointment = getAppointmentByDoctorAndID(doctorID, appointmentID);
            appointment.setAppointmentStatus(AppointmentStatus.REJECTED);
            updateAppointment(appointment);
        } catch (Exception e) {
            throw new ModelNotFoundException("Appointment not found.");
        }
    }

}
