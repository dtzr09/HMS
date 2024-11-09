package database.user;

import model.appointment.Appointment;
import model.user.Doctor;
import model.user.PersonalInfo;
import model.user.enums.Gender;
import utils.utils.ConvertToArrayList;
import utils.utils.FormatDateTime;
import utils.utils.StringToMap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import database.Database;

public class DoctorDatabase extends Database<Doctor> {

    private static final String FILE_PATH = "./data/user/doctor.txt";

    DoctorDatabase() {
        super();
        load();
    }

    /**
     * Gets a new instance of DoctorDatabase.
     *
     * @return a new instance of DoctorDatabase
     */
    public static DoctorDatabase getDB() {
        return new DoctorDatabase();
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
            String genderStr = map.get("personalInfo_gender");
            String dateOfRegistration = map.get("personalInfo_dateOfRegistration");
            Gender gender = genderStr != null ? Gender.valueOf(genderStr.toUpperCase()) : null;
            String appointmentAvailabilityStr = map.get("appointmentAvailability");

            Map<String, List<String>> apppointmentAvailability = StringToMap.ToMap(appointmentAvailabilityStr);

            Date birthDate = dateOfBirth == null ? null : FormatDateTime.convertStringToDateTime(dateOfBirth);
            Date registrationDate = dateOfRegistration == null ? null
                    : FormatDateTime.convertStringToDateTime(dateOfRegistration);

            PersonalInfo personalInfo = new PersonalInfo(name, gender, age, birthDate, emailAddress, phoneNumber,
                    registrationDate);

            String doctorID = map.get("doctorID");
            String password = map.get("password");

            Doctor doctor = new Doctor(doctorID, password, personalInfo, new ArrayList<>(), 0, new ArrayList<>(),
                    apppointmentAvailability);
            getAll().add(doctor);
        }
    }

    public List<Doctor> getAllDoctors() {
        return super.getAll();
    }

}
