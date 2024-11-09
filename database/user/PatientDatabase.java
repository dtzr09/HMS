package database.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import database.Database;
import model.user.Patient;
import model.user.PersonalInfo;
import model.user.enums.Gender;
import utils.utils.FormatDateTime;

public class PatientDatabase extends Database<Patient> {

    private static final String FILE_PATH = "./data/user/patient.txt";

    PatientDatabase() {
        super();
        load();
    }

    /**
     * Gets a new instance of PatientDatabase.
     *
     * @return a new instance of PatientDatabase
     */
    public static PatientDatabase getDB() {
        return new PatientDatabase();
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

            Date birthDate = dateOfBirth == null ? null : FormatDateTime.convertStringToDate(dateOfBirth);
            Date registrationDate = dateOfRegistration == null ? null
                    : FormatDateTime.convertStringToDate(dateOfRegistration);

            PersonalInfo personalInfo = new PersonalInfo(name, gender, age, birthDate, emailAddress, phoneNumber,
                    registrationDate);

            String patientID = map.get("patientID");
            String password = map.get("password");
            String doctorID = map.get("doctorID");

            Patient patient = new Patient(patientID, personalInfo, password, doctorID);

            getAll().add(patient);
        }
    }

    public List<Patient> getAllPatients() {
        return super.getAll();
    }

}
