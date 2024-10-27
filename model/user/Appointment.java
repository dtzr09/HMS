package model.user;

import java.util.Date;

public class Appointment {
    private String appointmentID;
    private AppointmentStatus appointmentStatus;
    private TimeSlot timeSlot;

    public Appointment(String appointmentID, AppointmentStatus appointmentStatus, TimeSlot timeSlot) {
        this.appointmentID = appointmentID;
        this.appointmentStatus = appointmentStatus;
        this.timeSlot = timeSlot;
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

    public void displayAppointment() {
        System.out.println("Appointment ID: " + appointmentID);
        System.out.println("Status: " + appointmentStatus);
        System.out.println("Time Slot: " + timeSlot.toString());
    }
}
