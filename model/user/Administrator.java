package model.user;

import java.util.List;
import java.util.Map;

public class Administrator implements User {
    private String administratorID;
    private PersonalInfo personalInfo;
    private String hashedPassword;
    private List<ReplenishmentRequest> requests = null;

    public Administrator(String administratorID, PersonalInfo personalInfo, String hashedPassword,
            List<ReplenishmentRequest> requests) {
        this.administratorID = administratorID;
        this.personalInfo = personalInfo;
        this.hashedPassword = hashedPassword;
        this.requests = requests;
    }

    public Administrator(String administratorID, PersonalInfo personalInfo, String hashedPassword) {
        this.administratorID = administratorID;
        this.personalInfo = personalInfo;
        this.hashedPassword = hashedPassword;
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

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public List<ReplenishmentRequest> getAllRequests() {
        return requests;
    }

    public String getEmail() {
        return personalInfo.getEmail();
    }
}
