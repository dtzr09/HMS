package model.appointment;

import java.util.Date;

import model.appointment.enums.AppointmentStatus;

public class Appointment {
    private String appointmentID;
    private AppointmentStatus appointmentStatus;
    private Date dateOfAppointment;
    private int timeOfAppointment;
    private String patientID;
    private String doctorID;

    public Appointment(String appointmentID, AppointmentStatus appointmentStatus, String patientID,
            Date dateOfAppointment, int appointmentTime, String doctorID) {
        this.appointmentID = appointmentID;
        this.appointmentStatus = appointmentStatus;
        this.patientID = patientID;
        this.dateOfAppointment = dateOfAppointment;
        this.timeOfAppointment = appointmentTime;
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

    public void setDateOfAppointment(Date date) {
        this.dateOfAppointment = date;
    }

    public Date getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setTimeOfAppointment(int time) {
        this.timeOfAppointment = time;
    }

    public int getTimeOfAppointment() {
        return timeOfAppointment;
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
