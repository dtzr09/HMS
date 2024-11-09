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

public class AppointmentManager {
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

    public static Appointment getAppointmentByID(String patientID, String appointmentID) {
        try {
            return AppointmentDatabase.getDB().getByID(appointmentID);
        } catch (Exception e) {
            System.out.println("Appointment not found.");
        }
        return null;
    }

    public static void createAppointment(Appointment appointment) throws ModelAlreadyExistsException {
        AppointmentDatabase.getDB().add(appointment);
    }

    public static void scheduleNewAppointment(String patientID, String doctorID, int timeSlotID,
            Date appointmentDate) throws Exception {
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

}
