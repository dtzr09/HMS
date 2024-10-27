package model.user;

import java.util.Map;

public class Pharmacist implements User {
    private String pharmacistID;
    private PersonalInfo personalInfo;
    private String hashedPassword;

    // Constructor
    public Pharmacist(String pharmacistID, PersonalInfo personalInfo, String hashedPassword) {
        this.pharmacistID = pharmacistID;
        this.personalInfo = personalInfo;
        this.hashedPassword = hashedPassword;
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

    public String getModelID() {
        return pharmacistID;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    // setters
    public void setName(String name) {
        personalInfo.setName(name);
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }
}
