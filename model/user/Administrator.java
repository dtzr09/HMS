package model.user;

import java.util.List;
import java.util.Map;

public class Administrator implements User {
    private String administratorID;
    private PersonalInfo personalInfo;
    private String password;
    private List<ReplenishmentRequest> requests = null;

    public Administrator(String administratorID, PersonalInfo personalInfo, String password,
            List<ReplenishmentRequest> requests) {
        this.administratorID = administratorID;
        this.personalInfo = personalInfo;
        this.password = password;
        this.requests = requests;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ReplenishmentRequest> getAllRequests() {
        return requests;
    }

    public String getEmail() {
        return personalInfo.getEmail();
    }
}
