package model.user;

import java.util.Date;
import java.util.Map;

import model.user.enums.Gender;

public class Pharmacist implements User {
    private String pharmacistID;
    private PersonalInfo personalInfo;
    private String password;

    // Constructor
    public Pharmacist(String pharmacistID, PersonalInfo personalInfo, String password) {
        this.pharmacistID = pharmacistID;
        this.personalInfo = personalInfo;
        this.password = password;
    }

    /**
     * Converts the map to a Pharmacist object
     *
     * @param map the map
     */
    public Pharmacist(Map<String, String> map) {
        this.convertToObject(map);
    }

    // getters
    public String getName() {
        return personalInfo.getName();
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public String getEmail() {
        return personalInfo.getEmail();
    }

    public String getModelID() {
        return pharmacistID;
    }

    public String getPassword() {
        return password;
    }

    public String getModelEmail() {
        return personalInfo.getEmail();
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
    public void setName(String name) {
        personalInfo.setName(name);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public void setEmail(String email) {
        personalInfo.setEmail(email);
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
}
