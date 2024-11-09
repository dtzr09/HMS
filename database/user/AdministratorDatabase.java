package database.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import database.Database;
import model.user.Administrator;
import model.user.PersonalInfo;
import model.user.enums.Gender;
import utils.utils.FormatDateTime;

public class AdministratorDatabase extends Database<Administrator> {

    private static final String FILE_PATH = "./data/user/administrator.txt";

    AdministratorDatabase() {
        super();
        load();
    }

    /**
     * Gets a new instance of AdministratorDatabase.
     *
     * @return a new instance of AdministratorDatabase
     */
    public static AdministratorDatabase getDB() {
        return new AdministratorDatabase();
    }

    /**
     * Gets the file path of the Database.
     *
     * @return the file path of the Database
     */
    @Override
    public String getFilePath() {
        return FILE_PATH;
    }

    /**
     * Sets the list of mappable objects in the Database.
     *
     * @param listOfMappableObjects the list of mappable objects to set
     */
    @Override
    public void setAll(List<Map<String, String>> listOfMappableObjects) {
        for (Map<String, String> map : listOfMappableObjects) {
            String name = map.get("personalInfo_name");
            String emailAddress = map.get("personalInfo_emailAddress");
            String phoneNumber = map.get("personalInfo_phoneNumber");
            String ageStr = map.get("personalInfo_age");
            Integer age = ageStr != null ? Integer.parseInt(ageStr) : null;
            String dateOfBirth = map.get("personalInfo_dateOfBirth");
            String dateOfRegistration = map.get("personalInfo_dateOfRegistration");
            String genderStr = map.get("personalInfo_gender");
            Gender gender = genderStr != null ? Gender.valueOf(genderStr.toUpperCase()) : null;

            Date birthDate = dateOfBirth == null ? null : FormatDateTime.convertStringToDateTime(dateOfBirth);
            Date registrationDate = dateOfRegistration == null ? null
                    : FormatDateTime.convertStringToDateTime(dateOfRegistration);

            PersonalInfo personalInfo = new PersonalInfo(name, gender, age, birthDate, emailAddress, phoneNumber,
                    registrationDate);
            String administratorID = map.get("administratorID");
            String password = map.get("password");

            Administrator admin = new Administrator(administratorID, personalInfo, password);

            getAll().add(admin);
        }
    }

    public List<Administrator> getAllAdministrators() {
        return super.getAll();
    }
}
