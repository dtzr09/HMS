package model.user;

import java.util.Date;

public class Appointment {
    private String appointmentID;
    private AppointmentStatus appointmentStatus;
    private TimeSlot timeSlot;
    private Patient patient;
    private Doctor doctor;

    public Appointment(String appointmentID, AppointmentStatus appointmentStatus, TimeSlot timeSlot, Patient patient,
            Doctor doctor) {
        this.appointmentID = appointmentID;
        this.appointmentStatus = appointmentStatus;
        this.timeSlot = timeSlot;
        this.patient = patient;
        this.doctor = doctor;
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

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void displayAppointment() {
        System.out.println("Appointment ID: " + appointmentID);
        System.out.println("Status: " + appointmentStatus);
        System.out.println("Time Slot: " + timeSlot.toString());
    }

}
