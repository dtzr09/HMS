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

    /**
     * Constructs an AppointmentOutcome with all specified fields.
     *
     * @param appointmentOutcomeID the unique identifier for the appointment
     *                             outcome.
     * @param patientID            the unique identifier for the patient.
     * @param doctorID             the unique identifier for the doctor.
     * @param typeOfService        the type of service provided.
     * @param consultationNotes    notes from the consultation.
     * @param diagnosisID          the unique identifier for the diagnosis.
     * @param appointmentID        the unique identifier for the appointment.
     * @param status               the status of the appointment outcome.
     */
    public AppointmentOutcome(String appointmentOutcomeID, String patientID,
            String doctorID, String typeOfService, String consultationNotes,
            String diagnosisID, String appointmentID, AppointmentOutcomeStatus status) {
        this.appointmentOutcomeID = appointmentOutcomeID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.typeOfService = typeOfService;
        this.consultationNotes = consultationNotes;
        this.diagnosisID = diagnosisID;
        this.appointmentID = appointmentID;
        this.status = status;
    }

    /**
     * Constructs an AppointmentOutcome with basic fields.
     *
     * @param appointmentOutcomeID the unique identifier for the appointment
     *                             outcome.
     * @param patientID            the unique identifier for the patient.
     * @param doctorID             the unique identifier for the doctor.
     * @param appointmentID        the unique identifier for the appointment.
     * @param status               the status of the appointment outcome.
     */
    public AppointmentOutcome(String appointmentOutcomeID, String patientID, String doctorID,
            String appointmentID, AppointmentOutcomeStatus status) {
        this.appointmentOutcomeID = appointmentOutcomeID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.appointmentID = appointmentID;
        this.status = status;
    }

    /**
     * Constructs an AppointmentOutcome by converting a map of attributes.
     *
     * @param map a map of appointment outcome attributes.
     */
    public AppointmentOutcome(Map<String, String> map) {
        this.convertToObject(map);
    }

    /**
     * Retrieves the model ID.
     *
     * @return the unique identifier for the appointment outcome.
     */
    public String getModelID() {
        return appointmentOutcomeID;
    }

    /**
     * Retrieves the appointment outcome ID.
     *
     * @return the unique identifier for the appointment outcome.
     */
    public String getAppointmentOutcomeID() {
        return appointmentOutcomeID;
    }

    /**
     * Retrieves the patient ID.
     *
     * @return the unique identifier for the patient.
     */
    public String getPatientID() {
        return patientID;
    }

    /**
     * Retrieves the doctor ID.
     *
     * @return the unique identifier for the doctor.
     */
    public String getDoctorID() {
        return doctorID;
    }

    /**
     * Retrieves the type of service provided.
     *
     * @return the type of service.
     */
    public String getTypeOfService() {
        return typeOfService;
    }

    /**
     * Retrieves the consultation notes.
     *
     * @return the consultation notes.
     */
    public String getConsultationNotes() {
        return consultationNotes;
    }

    /**
     * Retrieves the diagnosis ID.
     *
     * @return the unique identifier for the diagnosis.
     */
    public String getDiagnosisID() {
        return diagnosisID;
    }

    /**
     * Retrieves the appointment ID.
     *
     * @return the unique identifier for the appointment.
     */
    public String getAppointmentID() {
        return appointmentID;
    }

    /**
     * Retrieves the status of the appointment outcome.
     *
     * @return the status of the appointment outcome.
     */
    public AppointmentOutcomeStatus getStatus() {
        return status;
    }

    /**
     * Sets the consultation notes.
     *
     * @param consultationNotes the consultation notes to set.
     */
    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    /**
     * Sets the diagnosis ID.
     *
     * @param diagnosisID the diagnosis ID to set.
     */
    public void setDiagnosisID(String diagnosisID) {
        this.diagnosisID = diagnosisID;
    }

    /**
     * Sets the type of service.
     *
     * @param typeOfService the type of service to set.
     */
    public void setTypeOfService(String typeOfService) {
        this.typeOfService = typeOfService;
    }

    /**
     * Sets the doctor ID.
     *
     * @param doctorID the doctor ID to set.
     */
    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    /**
     * Sets the patient ID.
     *
     * @param patientID the patient ID to set.
     */
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    /**
     * Sets the appointment outcome ID.
     *
     * @param appointmentOutcomeID the appointment outcome ID to set.
     */
    public void setAppointmentOutcomeID(String appointmentOutcomeID) {
        this.appointmentOutcomeID = appointmentOutcomeID;
    }

    /**
     * Sets the appointment ID.
     *
     * @param appointmentID the appointment ID to set.
     */
    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * Sets the status of the appointment outcome.
     *
     * @param status the status to set.
     */
    public void setStatus(AppointmentOutcomeStatus status) {
        this.status = status;
    }
}
