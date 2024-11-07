package model.appointment;

import java.util.Date;

import model.appointment.enums.AppointmentStatus;

public class Appointment {
    private String appointmentID;
    private AppointmentStatus appointmentStatus;
    private TimeSlot timeSlot;
    private String patientID;
    private String doctorID;

    public Appointment(String appointmentID, AppointmentStatus appointmentStatus, TimeSlot timeSlot, String patientID,
            String doctorID) {
        this.appointmentID = appointmentID;
        this.appointmentStatus = appointmentStatus;
        this.timeSlot = timeSlot;
        this.patientID = patientID;
        this.doctorID = doctorID;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public boolean setAppointmentStatus(AppointmentStatus status) {
        this.appointmentStatus = status;
        return true;
    }

    public boolean updateAppointmentStatus(AppointmentStatus newStatus) {
        this.appointmentStatus = newStatus;
        return true;
    }

    public Date setDateOfAppointment(Date date) {
        timeSlot.setDate(date);
        return timeSlot.getDate();
    }

    public Date getDateOfAppointment() {
        return timeSlot.getDate();
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

}
