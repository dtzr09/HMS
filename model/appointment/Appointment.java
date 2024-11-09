package model.appointment;

import java.util.Map;

import model.Model;
import model.appointment.enums.AppointmentStatus;

public class Appointment implements Model {
    private String appointmentID;
    private AppointmentStatus appointmentStatus;
    private String dateOfAppointment;
    private int timeOfAppointment;
    private String patientID;
    private String doctorID;

    public Appointment(String appointmentID, AppointmentStatus appointmentStatus, String patientID,
            String dateOfAppointment, int appointmentTime, String doctorID) {
        this.appointmentID = appointmentID;
        this.appointmentStatus = appointmentStatus;
        this.patientID = patientID;
        this.dateOfAppointment = dateOfAppointment;
        this.timeOfAppointment = appointmentTime;
        this.doctorID = doctorID;
    }

    public Appointment(Map<String, String> map) {
        this.convertToObject(map);
    }

    public String getModelID() {
        return appointmentID;
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

    public void setDateOfAppointment(String date) {
        this.dateOfAppointment = date;
    }

    public String getDateOfAppointment() {
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
