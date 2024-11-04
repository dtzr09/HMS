package controller.appointment;

import java.util.ArrayList;
import java.util.List;

import model.appointment.Appointment;

enum Status {
    SCHEDULED, COMPLETED, CANCELLED
}

public class AppointmentManager {
    private List<Appointment> appointments = new ArrayList<>();

    // public Appointment scheduleAppointment(String doctorID, String patientID,
    // TimeSlot timeSlot) {
    // Appointment appointment = new Appointment("appt" + (appointments.size() + 1),
    // doctorID, patientID, timeSlot);
    // appointments.add(appointment);
    // return appointment;
    // }

    // public List<Appointment> getAppointmentsForPatient(String patientID) {
    // List<Appointment> patientAppointments = new ArrayList<>();
    // for (Appointment appointment : appointments) {
    // if (appointment.getPatientID().equals(patientID)) {
    // patientAppointments.add(appointment);
    // }
    // }
    // return patientAppointments;
    // }

    // public void cancelAppointment(Appointment appointment) {
    // appointment.setStatus(Status.CANCELLED);
    // System.out.println("Appointment cancelled: " + appointment);
    // }

    // public void completeAppointment(Appointment appointment) {
    // appointment.setStatus(Status.COMPLETED);
    // System.out.println("Appointment completed: " + appointment);
    // }
}
