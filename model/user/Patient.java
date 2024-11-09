package model.user;

import model.user.enums.BloodType;
import model.user.enums.Gender;

import java.util.Map;
import java.util.ArrayList;
import java.util.Date;

public class Patient implements User {
    private String patientID;
    private String doctorID;
    private String password;
    private PersonalInfo personalInfo;
    private ArrayList<String> allergies;
    private BloodType bloodType;
    private ArrayList<String> diagnosisIDs = new ArrayList<>();

    public Patient() {
    }

    public Patient(String patientID, String password, PersonalInfo personalInfo, ArrayList<String> allergies,
            BloodType bloodType,
            ArrayList<String> diagnosisIDs, String doctorID) {
        this.patientID = patientID;
        this.password = password;
        this.personalInfo = personalInfo;
        this.allergies = allergies;
        this.bloodType = bloodType;
        this.diagnosisIDs = diagnosisIDs;
        this.doctorID = doctorID;
    }

    public Patient(String patientID, PersonalInfo personalInfo, String password, String doctorID) {
        this.patientID = patientID;
        this.password = password;
        this.personalInfo = personalInfo;
        this.doctorID = doctorID;
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

    public ArrayList<String> getAllergies() {
        return allergies;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public ArrayList<String> getDiagnosisID() {
        return diagnosisIDs;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public Date getDateOfBirth() {
        return personalInfo.getDateOfBirth();
    }

    public int getAge() {
        return personalInfo.getAge();
    }

    public String getPhoneNumber() {
        return personalInfo.getPhoneNumber();
    }

    public Gender getGender() {
        return personalInfo.getGender();
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

    public void setAllergies(ArrayList<String> allergies) {
        this.allergies = allergies;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public void setDiagnosisID(ArrayList<String> diagnosisIDs) {
        this.diagnosisIDs = diagnosisIDs;
    }

    public void addDiagnosisID(String diagnosisID) { // For adding one new diagnosis
        this.diagnosisIDs.add(diagnosisID);
    }

    public void setEmail(String email) {
        personalInfo.setEmail(email);
    }

    public void setName(String name) {
        personalInfo.setName(name);
    }

    public void setPhoneNumber(String phoneNumber) {
        personalInfo.setPhoneNumber(phoneNumber);
    }

    public void setAge(int age) {
        personalInfo.setAge(age);
    }

    public void setDateOfBirth(Date dateOfBirth) {
        personalInfo.setDateOfBirth(dateOfBirth);
    }

    public void addAllergy(String allergy) {
        this.allergies.add(allergy);
    }

}
