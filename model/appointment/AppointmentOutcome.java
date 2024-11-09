package model.appointment;

import java.util.Map;

import model.Model;
import model.prescription.Prescription;

public class AppointmentOutcome implements Model {
    private String appointmentOutcomeID;
    private String patientID;
    private String doctorID;
    private String typeOfService;
    private String consultationNotes;
    private Prescription prescription;
    private String appointmentID;

    public AppointmentOutcome(String appointmentOutcomeID, String patientID,
            String doctorID,
            String typeOfService, String consultationNotes, Prescription prescription, String appointmentID) {
        this.appointmentOutcomeID = appointmentOutcomeID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.typeOfService = typeOfService;
        this.consultationNotes = consultationNotes;
        this.prescription = prescription;
        this.appointmentID = appointmentID;
    }

    /**
     * Converts the map to a AppointmentOutcome object
     *
     * @param map the map
     */
    public AppointmentOutcome(Map<String, String> map) {
        this.convertToObject(map);
    }

    public String getModelID() {
        return appointmentOutcomeID;
    }

    public String getAppointmentOutcomeID() {
        return appointmentOutcomeID;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public String getTypeOfService() {
        return typeOfService;
    }

    public String getConsultationNotes() {
        return consultationNotes;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public void setTypeOfService(String typeOfService) {
        this.typeOfService = typeOfService;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public void setAppointmentOutcomeID(String appointmentOutcomeID) {
        this.appointmentOutcomeID = appointmentOutcomeID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

}
