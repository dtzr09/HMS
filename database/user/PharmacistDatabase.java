package database.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import database.Database;
import model.user.PersonalInfo;
import model.user.Pharmacist;
import model.user.enums.Gender;
import utils.utils.FormatDateTime;

public class PharmacistDatabase extends Database<Pharmacist> {
    private static final String FILE_PATH = "./data/user/pharmacist.txt";

    PharmacistDatabase() {
        super();
        load();
    }

    /**
     * Gets a new instance of PharmacistDatabase.
     *
     * @return a new instance of PharmacistDatabase
     */
    public static PharmacistDatabase getDB() {
        return new PharmacistDatabase();
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

            Date birthDate = FormatDateTime.convertStringToDate(dateOfBirth);
            Date registrationDate = FormatDateTime.convertStringToDate(dateOfRegistration);

            PersonalInfo personalInfo = new PersonalInfo(name, gender, age, birthDate, emailAddress, phoneNumber,
                    registrationDate);

            String pharmacistID = map.get("pharmacistID");
            String password = map.get("password");

            Pharmacist admin = new Pharmacist(pharmacistID, personalInfo, password);

            getAll().add(admin);
        }
    }

    public List<Pharmacist> getAllPharmacist() {
        return super.getAll();
    }
}
