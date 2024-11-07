package controller.appointment;

import java.util.ArrayList;
import java.util.List;

import controller.user.PatientManager;
import model.appointment.Appointment;
import model.user.Patient;

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
            Appointment appointment = PatientManager.getAppointmentByID(patientID, appointmentID);
            return appointment;
        } catch (Exception e) {
            System.out.println("Appointment not found.");
        }
        return null;
    }

}
