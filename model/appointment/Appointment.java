package model.appointment;

import java.util.Map;

import model.Model;
import model.appointment.enums.AppointmentStatus;

/**
 * Represents an appointment in the hospital management system.
 * Each appointment is identified by a unique ID and has an associated status,
 * patient ID, doctor ID, date, and time.
 */
public class Appointment implements Model {
    private String appointmentID;
    private AppointmentStatus appointmentStatus;
    private String dateOfAppointment;
    private int timeOfAppointment;
    private String patientID;
    private String doctorID;

    /**
     * Constructs a new Appointment with the specified details.
     *
     * @param appointmentID     the unique identifier of the appointment
     * @param appointmentStatus the current status of the appointment
     * @param patientID         the ID of the patient associated with this
     *                          appointment
     * @param dateOfAppointment the date of the appointment
     * @param appointmentTime   the time of the appointment in 24-hour format
     * @param doctorID          the ID of the doctor associated with this
     *                          appointment
     */
    public Appointment(String appointmentID, AppointmentStatus appointmentStatus, String patientID,
            String dateOfAppointment, int appointmentTime, String doctorID) {
        this.appointmentID = appointmentID;
        this.appointmentStatus = appointmentStatus;
        this.patientID = patientID;
        this.dateOfAppointment = dateOfAppointment;
        this.timeOfAppointment = appointmentTime;
        this.doctorID = doctorID;
    }

    /**
     * Constructs a new Appointment from a map of attributes.
     *
     * @param map a map of attributes where keys are attribute names and values are
     *            attribute values
     */
    public Appointment(Map<String, String> map) {
        this.convertToObject(map);
    }

    /**
     * Retrieves the unique model ID.
     *
     * @return the unique identifier of the appointment
     */
    public String getModelID() {
        return appointmentID;
    }

    /**
     * Retrieves the unique appointment ID.
     *
     * @return the identifier of the appointment
     */
    public String getAppointmentID() {
        return appointmentID;
    }

    /**
     * Retrieves the current status of the appointment.
     *
     * @return the status of the appointment
     */
    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    /**
     * Sets the status of the appointment.
     *
     * @param status the new status to set for the appointment
     * @return true if the status was set successfully
     */
    public boolean setAppointmentStatus(AppointmentStatus status) {
        this.appointmentStatus = status;
        return true;
    }

    /**
     * Updates the status of the appointment.
     *
     * @param newStatus the new status to update for the appointment
     * @return true if the status was updated successfully
     */
    public boolean updateAppointmentStatus(AppointmentStatus newStatus) {
        this.appointmentStatus = newStatus;
        return true;
    }

    /**
     * Sets the date of the appointment.
     *
     * @param date the date to set for the appointment
     */
    public void setDateOfAppointment(String date) {
        this.dateOfAppointment = date;
    }

    /**
     * Retrieves the date of the appointment.
     *
     * @return the date of the appointment
     */
    public String getDateOfAppointment() {
        return dateOfAppointment;
    }

    /**
     * Sets the time of the appointment.
     *
     * @param time the time to set for the appointment in 24-hour format
     */
    public void setTimeOfAppointment(int time) {
        this.timeOfAppointment = time;
    }

    /**
     * Retrieves the time of the appointment.
     *
     * @return the time of the appointment in 24-hour format
     */
    public int getTimeOfAppointment() {
        return timeOfAppointment;
    }

    /**
     * Retrieves the patient ID associated with the appointment.
     *
     * @return the patient ID
     */
    public String getPatientID() {
        return patientID;
    }

    /**
     * Retrieves the doctor ID associated with the appointment.
     *
     * @return the doctor ID
     */
    public String getDoctorID() {
        return doctorID;
    }

    /**
     * Sets the doctor ID for the appointment.
     *
     * @param doctorID the doctor ID to set for the appointment
     */
    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }
}
