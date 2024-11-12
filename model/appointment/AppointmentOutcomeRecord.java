package model.appointment;

import java.util.List;

import model.prescription.enums.PrescriptionStatus;

public class AppointmentOutcomeRecord {
    private String appointmentOutcomeID;
    private String typeOfService;
    private String consultationNotes;
    private List<String> medicationsNames;
    private String appointmentDate;
    private PrescriptionStatus prescriptionStatus;

    /**
     * Constructs an AppointmentOutcomeRecord with all specified fields.
     *
     * @param appointmentOutcomeID the unique identifier for the appointment
     *                             outcome.
     * @param typeOfService        the type of service provided.
     * @param consultationNotes    notes from the consultation.
     * @param medicationsNames     a list of medication names prescribed.
     * @param appointmentDate      the date of the appointment.
     * @param prescriptionStatus   the status of the prescription.
     */
    public AppointmentOutcomeRecord(String appointmentOutcomeID, String typeOfService, String consultationNotes,
            List<String> medicationsNames, String appointmentDate, PrescriptionStatus prescriptionStatus) {
        this.appointmentOutcomeID = appointmentOutcomeID;
        this.typeOfService = typeOfService;
        this.consultationNotes = consultationNotes;
        this.medicationsNames = medicationsNames;
        this.appointmentDate = appointmentDate;
        this.prescriptionStatus = prescriptionStatus;
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
     * Sets the appointment outcome ID.
     *
     * @param appointmentOutcomeID the appointment outcome ID to set.
     */
    public void setAppointmentOutcomeID(String appointmentOutcomeID) {
        this.appointmentOutcomeID = appointmentOutcomeID;
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
     * Sets the type of service provided.
     *
     * @param typeOfService the type of service to set.
     */
    public void setTypeOfService(String typeOfService) {
        this.typeOfService = typeOfService;
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
     * Sets the consultation notes.
     *
     * @param consultationNotes the consultation notes to set.
     */
    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    /**
     * Retrieves the list of medication names prescribed.
     *
     * @return the list of medication names.
     */
    public List<String> getMedicationsNames() {
        return medicationsNames;
    }

    /**
     * Sets the list of medication names prescribed.
     *
     * @param medicationsNames the list of medication names to set.
     */
    public void setMedicationsNames(List<String> medicationsNames) {
        this.medicationsNames = medicationsNames;
    }

    /**
     * Retrieves the appointment date.
     *
     * @return the date of the appointment.
     */
    public String getAppointmentDate() {
        return appointmentDate;
    }

    /**
     * Sets the appointment date.
     *
     * @param appointmentDate the date of the appointment to set.
     */
    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    /**
     * Retrieves the prescription status.
     *
     * @return the status of the prescription.
     */
    public PrescriptionStatus getPrescriptionStatus() {
        return prescriptionStatus;
    }

    /**
     * Sets the prescription status.
     *
     * @param prescriptionStatus the prescription status to set.
     */
    public void setPrescriptionStatus(PrescriptionStatus prescriptionStatus) {
        this.prescriptionStatus = prescriptionStatus;
    }

}
