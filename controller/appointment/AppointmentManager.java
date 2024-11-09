package controller.appointment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import controller.user.DoctorManager;
import controller.user.PatientManager;
import model.appointment.Appointment;
import model.appointment.enums.AppointmentStatus;
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

    public static void scheduleNewAppointment(String patientID, String doctorID, int timeSlotID,
            Date appointmentDate) throws Exception {
        String appointmentID = UUID.randomUUID().toString();
        Appointment newAppointment = new Appointment(appointmentID, AppointmentStatus.PENDING, patientID,
                appointmentDate,
                timeSlotID, doctorID);
        PatientManager.addAppointment(patientID, newAppointment);
        DoctorManager.addAppointmentRequest(doctorID, newAppointment);
    }

}
