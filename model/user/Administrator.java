package model.user;

import java.util.Date;
import java.util.Map;

import model.user.enums.Gender;

public class Administrator implements User {
    private String administratorID;
    private PersonalInfo personalInfo;
    private String password;

    public Administrator(String administratorID, PersonalInfo personalInfo, String password) {
        this.administratorID = administratorID;
        this.personalInfo = personalInfo;
        this.password = password;
    }

    /**
     * Converts the map to a Administrator object
     *
     * @param map the map
     */
    public Administrator(Map<String, String> map) {
        this.convertToObject(map);
    }

    public void displayPersonalInfo() {
        personalInfo.displayPersonalInfo();
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public String getName() {
        return personalInfo.getName();
    }

    public String getModelID() {
        return administratorID;
    }

    public String getModelEmail() {
        return personalInfo.getEmail();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
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

}
