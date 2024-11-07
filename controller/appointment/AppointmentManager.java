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

}
