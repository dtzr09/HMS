package model.appointment;

import java.util.List;

import model.prescription.PrescriptionStatus;

public class AppointmentOutcomeRecord {
    private String appointmentOutcomeID;
    private String typeOfService;
    private String consultationNotes;
    private List<String> medicationsNames;
    private String appointmentDate;
    private PrescriptionStatus prescriptionStatus;

    public AppointmentOutcomeRecord(String appointmentOutcomeID, String typeOfService, String consultationNotes,
            List<String> medicationsNames, String appointmentDate, PrescriptionStatus prescriptionStatus) {
        this.appointmentOutcomeID = appointmentOutcomeID;
        this.typeOfService = typeOfService;
        this.consultationNotes = consultationNotes;
        this.medicationsNames = medicationsNames;
        this.appointmentDate = appointmentDate;
        this.prescriptionStatus = prescriptionStatus;
    }

    public String getAppointmentOutcomeID() {
        return appointmentOutcomeID;
    }

    public void setAppointmentOutcomeID(String appointmentOutcomeID) {
        this.appointmentOutcomeID = appointmentOutcomeID;
    }

    public String getTypeOfService() {
        return typeOfService;
    }

    public void setTypeOfService(String typeOfService) {
        this.typeOfService = typeOfService;
    }

    public String getConsultationNotes() {
        return consultationNotes;
    }

    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    public List<String> getMedicationsNames() {
        return medicationsNames;
    }

    public void setMedicationsNames(List<String> medicationsNames) {
        this.medicationsNames = medicationsNames;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public PrescriptionStatus getPrescriptionStatus() {
        return prescriptionStatus;
    }

    public void setPrescriptionStatus(PrescriptionStatus prescriptionStatus) {
        this.prescriptionStatus = prescriptionStatus;
    }
}
