package model.user;

import model.appointment.Appointment;
import model.diagnosis.Diagnosis;
import model.user.enums.BloodType;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class Patient implements User {
    private String patientID;
    private String doctorID;
    private String password;
    private PersonalInfo personalInfo;
    private ArrayList<String> allergies;
    private BloodType bloodType;
    private ArrayList<Appointment> appointments = null;
    private ArrayList<Diagnosis> diagnosis = null;

    public Patient(String patientID, String password, PersonalInfo personalInfo, ArrayList<String> allergies,
            BloodType bloodType,
            ArrayList<Appointment> appointments, ArrayList<Diagnosis> diagnosis, String doctorID) {
        this.patientID = patientID;
        this.password = password;
        this.personalInfo = personalInfo;
        this.allergies = allergies;
        this.bloodType = bloodType;
        this.appointments = appointments;
        this.diagnosis = diagnosis;
        this.doctorID = doctorID;
    }

    public Patient(String patientID, PersonalInfo personalInfo, String password) {
        this.patientID = patientID;
        this.password = password;
        this.personalInfo = personalInfo;
    }

    /**
     * Converts the map to a Patient object
     *
     * @param map the map
     */
    public Patient(Map<String, String> map) {
        this.convertToObject(map);
    }

    // getters
    public String getModelID() {
        return this.patientID;
    }

    public String getPatientID() {
        return this.patientID;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return personalInfo.getEmail();
    }

    public String getModelEmail() {
        return personalInfo.getEmail();
    }

    public String getName() {
        return personalInfo.getName();
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public ArrayList<String> getAllergies() {
        return allergies;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public ArrayList<Diagnosis> getDiagnosis() {
        return diagnosis;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void printAllDiagnosis() {
        for (Diagnosis diagnosis : this.diagnosis) {
            System.out.println("Diagnosis ID: " + diagnosis.getDiagnosisID() + " , " + diagnosis.getDisease());
        }
    }

    public Diagnosis getOneDiagnosis(String id) {
        for (Diagnosis oneDiagnosis : this.diagnosis) {
            if (oneDiagnosis.getDiagnosisID().equals(id)) {
                return oneDiagnosis;
            }
        }
        throw new NoSuchElementException("Diagnosis with ID " + id + " not found!");
    }

    // setters
    public void setPassword(String password) {
        this.password = password;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void setAllergies(ArrayList<String> allergies) {
        this.allergies = allergies;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public void setDiagnosis(ArrayList<Diagnosis> diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void addDiagnosis(Diagnosis diagnosis) { // For adding one new diagnosis
        this.diagnosis.add(diagnosis);
    }
}
