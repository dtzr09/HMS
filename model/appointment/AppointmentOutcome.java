package model.appointment;

import java.util.Map;

import model.Model;
import model.appointment.enums.AppointmentOutcomeStatus;

public class AppointmentOutcome implements Model {
    private String appointmentOutcomeID;
    private String patientID;
    private String doctorID;
    private String typeOfService;
    private String consultationNotes;
    private String diagnosisID;
    private String appointmentID;
    private AppointmentOutcomeStatus status;

    public AppointmentOutcome(String appointmentOutcomeID, String patientID,
            String doctorID,
            String typeOfService, String consultationNotes, String diagnosisID, String appointmentID,
            AppointmentOutcomeStatus status) {
        this.appointmentOutcomeID = appointmentOutcomeID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.typeOfService = typeOfService;
        this.consultationNotes = consultationNotes;
        this.diagnosisID = diagnosisID;
        this.appointmentID = appointmentID;
        this.status = status;
    }

    public AppointmentOutcome(String appointmentOutcomeID, String patientID, String doctorID, String appointmentID,
            AppointmentOutcomeStatus status) {
        this.appointmentOutcomeID = appointmentOutcomeID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.appointmentID = appointmentID;
        this.status = status;
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

    public String getDiagnosisID() {
        return diagnosisID;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public AppointmentOutcomeStatus getStatus() {
        return status;
    }

    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    public void setDiagnosisID(String diagnosisID) {
        this.diagnosisID = diagnosisID;
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

    public void setStatus(AppointmentOutcomeStatus status) {
        this.status = status;
    }

}
