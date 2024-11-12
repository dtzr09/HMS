package controller.user;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import database.user.AdministratorDatabase;
import database.user.DoctorDatabase;
import database.user.PatientDatabase;
import database.user.PharmacistDatabase;
import model.user.Administrator;
import model.user.Doctor;
import model.user.Patient;
import model.user.PersonalInfo;
import model.user.Pharmacist;
import model.user.User;
import model.user.enums.Gender;
import model.user.enums.UserType;
import utils.exceptions.ModelAlreadyExistsException;
import utils.exceptions.ModelNotFoundException;
import utils.exceptions.UserCannotBeFoundException;

public class UserManager {

    /**
     * Find doctor by email
     * 
     * @param email
     * @return User
     * @throws ModelNotFoundException
     */
    private static User findDoctorByEmail(String email) throws ModelNotFoundException {
        return DoctorDatabase.getDB().getByEmail(email);
    }

    /**
     * Find patient by email
     * 
     * @param email
     * @return User
     * @throws ModelNotFoundException
     */
    private static User findPatientByEmail(String email) throws ModelNotFoundException {
        return PatientDatabase.getDB().getByEmail(email);
    }

    /**
     * Find pharmacist by email
     * 
     * @param email
     * @return User
     * @throws ModelNotFoundException
     */
    private static User findPhamarcistByEmail(String email) throws ModelNotFoundException {
        return PharmacistDatabase.getDB().getByEmail(email);
    }

    /**
     * Find administrator by email
     * 
     * @param email
     * @return User
     * @throws ModelNotFoundException
     */
    private static User findAdministratorByEmail(String email) throws ModelNotFoundException {
        return AdministratorDatabase.getDB().getByEmail(email);
    }

    /**
     * Find user by email
     * 
     * @param email
     * @param userType
     * @return User
     * @throws ModelNotFoundException
     */
    public static User findUser(String email, UserType userType) throws ModelNotFoundException {
        return switch (userType) {
            case DOCTOR -> findDoctorByEmail(email);
            case PATIENT -> findPatientByEmail(email);
            case PHARMACIST -> findPhamarcistByEmail(email);
            case ADMINISTRATOR -> findAdministratorByEmail(email);
        };
    }

    /**
     * Find doctor by ID
     * 
     * @param doctor
     * @return User
     * @throws ModelNotFoundException
     */
    private static User findDoctorById(String doctorID) throws ModelNotFoundException {
        return DoctorDatabase.getDB().getByID(doctorID);
    }

    /**
     * Find patient by ID
     * 
     * @param patientID
     * @return User
     * @throws ModelNotFoundException
     */
    private static User findPatientById(String patientID) throws ModelNotFoundException {
        return PatientDatabase.getDB().getByID(patientID);
    }

    /**
     * Find pharmacist by ID
     * 
     * @param pharmacistID
     * @return User
     * @throws ModelNotFoundException
     */
    private static User findPharmacistById(String pharmacistID) throws ModelNotFoundException {
        return PharmacistDatabase.getDB().getByID(pharmacistID);
    }

    /**
     * Find administrator by ID
     * 
     * @param administratorID
     * @return User
     * @throws ModelNotFoundException
     */
    private static User findAdministratorById(String administratorID) throws ModelNotFoundException {
        return AdministratorDatabase.getDB().getByID(administratorID);
    }

    /**
     * Find user by ID
     * 
     * @param userID
     * @param userType
     * @return User
     * @throws ModelNotFoundException
     */
    public static User findUserById(String userID, UserType userType) throws ModelNotFoundException {
        return switch (userType) {
            case DOCTOR -> findDoctorById(userID);
            case PATIENT -> findPatientById(userID);
            case PHARMACIST -> findPharmacistById(userID);
            case ADMINISTRATOR -> findAdministratorById(userID);
        };
    }

    /**
     * Update pharmacist
     * 
     * @param pharmacist
     * @throws ModelNotFoundException
     */
    private static void updatePharmacist(Pharmacist pharmacist) throws ModelNotFoundException {
        PharmacistDatabase.getDB().update(pharmacist);
    }

    /**
     * Update doctor
     * 
     * @param doctor
     * @throws ModelNotFoundException
     */
    private static void updateDoctor(Doctor doctor) throws ModelNotFoundException {
        DoctorDatabase.getDB().update(doctor);
    }

    /**
     * Update patient
     * 
     * @param patient
     * @throws ModelNotFoundException
     */
    private static void updatePatient(Patient patient) throws ModelNotFoundException {
        PatientDatabase.getDB().update(patient);
    }

    /**
     * Update administrator
     * 
     * @param administrator
     * @throws ModelNotFoundException
     */
    private static void updateAdministrator(Administrator administrator) throws ModelNotFoundException {
        AdministratorDatabase.getDB().update(administrator);
    }

    /**
     * Update user
     * 
     * @param user
     * @throws ModelNotFoundException
     */
    public static void updateUser(User user) throws ModelNotFoundException {
        if (user instanceof Doctor doctor) {
            updateDoctor(doctor);
        } else if (user instanceof Patient patient) {
            updatePatient(patient);
        } else if (user instanceof Pharmacist pharmacist) {
            updatePharmacist(pharmacist);
        } else if (user instanceof Administrator administrator) {
            updateAdministrator(administrator);
        }
    }

    /**
     * Update user profile
     * 
     * @param user
     * @param userType
     * @param updatedValues
     * @throws ModelNotFoundException
     */
    public static void updateUserProfile(User user, UserType userType, Map<String, String> updatedValues)
            throws ModelNotFoundException {
        for (Map.Entry<String, String> entry : updatedValues.entrySet()) {
            switch (entry.getKey()) {
                case "Name" -> user.setName(entry.getValue());
                case "Email" -> user.setEmail(entry.getValue());
                case "Phone Number" -> user.setPhoneNumber(entry.getValue());
                case "Age" -> user.setAge(Integer.parseInt(entry.getValue()));
                case "Date of Birth" -> {
                    user.setDateOfBirth(entry.getValue());
                }
            }
        }
        switch (userType) {
            case UserType.DOCTOR ->
                updateDoctor((Doctor) user);
            case UserType.PATIENT ->
                updatePatient((Patient) user);
            case UserType.PHARMACIST ->
                updatePharmacist((Pharmacist) user);
            case UserType.ADMINISTRATOR ->
                updateAdministrator((Administrator) user);
        }
    }

    /**
     * Create doctor
     * 
     * @param doctor
     * @throws ModelAlreadyExistsException
     */
    private static void createDoctor(Doctor doctor) throws ModelAlreadyExistsException {
        DoctorDatabase.getDB().add(doctor);
    }

    /**
     * Load doctor
     * 
     * @param pharmacist
     * @throws ModelAlreadyExistsException
     */
    public static void loadDoctor(Doctor doctor) throws ModelAlreadyExistsException {
        createDoctor(doctor);
    }

    /**
     * Load patient
     * 
     * @param patient
     * @throws ModelAlreadyExistsException
     */
    public static void loadPatient(Patient patient) throws ModelAlreadyExistsException {
        createPatient(patient);
    }

    /**
     * Create patient
     * 
     * @param patient
     * @throws ModelAlreadyExistsException
     */
    private static void createPatient(Patient patient) throws ModelAlreadyExistsException {
        PatientDatabase.getDB().add(patient);
    }

    /**
     * Create pharmacist
     * 
     * @param pharmacist
     * @throws ModelAlreadyExistsException
     */
    private static void createPharmacist(Pharmacist pharmacist) throws ModelAlreadyExistsException {
        PharmacistDatabase.getDB().add(pharmacist);
    }

    /**
     * Create administrator
     * 
     * @param administrator
     * @throws ModelAlreadyExistsException
     */
    private static void createAdministrator(Administrator administrator) throws ModelAlreadyExistsException {
        AdministratorDatabase.getDB().add(administrator);
    }

    /**
     * Create user
     * 
     * @param email
     * @param name
     * @param gender
     * @param age
     * @param userType
     * @param password
     * @return User
     * @throws ModelNotFoundException
     * @throws ModelAlreadyExistsException
     */
    public static User createUser(String email, String name, Gender gender, int age, UserType userType, String password)
            throws ModelNotFoundException, ModelAlreadyExistsException {

        Date dateOfRegistration = new Date();
        PersonalInfo personalInfo = new PersonalInfo(name, email, gender, age, dateOfRegistration);
        String userID = UUID.randomUUID().toString();
        User user = switch (userType) {
            case DOCTOR -> new Doctor(userID, personalInfo, password);
            case PATIENT -> new Patient(userID, personalInfo, password, DoctorManager.getRandDoctorID());
            case PHARMACIST -> new Pharmacist(userID, personalInfo, password);
            case ADMINISTRATOR -> new Administrator(userID, personalInfo, password);
        };
        if (user instanceof Doctor doctor) {
            createDoctor(doctor);
        } else if (user instanceof Patient patient) {
            createPatient(patient);
        } else if (user instanceof Pharmacist pharmacist) {
            createPharmacist(pharmacist);
        } else if (user instanceof Administrator administrator) {
            createAdministrator(administrator);
        }
        return user;

    }

    /**
     * Create user with UUID
     * 
     * @param userID
     * @param email
     * @param name
     * @param gender
     * @param age
     * @param userType
     * @param password
     * @return User
     * @throws ModelNotFoundException
     * @throws ModelAlreadyExistsException
     */
    public static User createUserWithUUID(String userID, String email, String name, Gender gender, int age,
            UserType userType, String password)
            throws ModelNotFoundException, ModelAlreadyExistsException {

        Date dateOfRegistration = new Date();
        PersonalInfo personalInfo = new PersonalInfo(name, email, gender, age, dateOfRegistration);
        User user = switch (userType) {
            case DOCTOR -> new Doctor(userID, personalInfo, password);
            case PATIENT -> new Patient(userID, personalInfo, password, null);
            case PHARMACIST -> new Pharmacist(userID, personalInfo, password);
            case ADMINISTRATOR -> new Administrator(userID, personalInfo, password);
        };
        if (user instanceof Doctor doctor) {
            createDoctor(doctor);
        } else if (user instanceof Patient patient) {
            createPatient(patient);
        } else if (user instanceof Pharmacist pharmacist) {
            createPharmacist(pharmacist);
        } else if (user instanceof Administrator administrator) {
            createAdministrator(administrator);
        }
        return user;

    }

    /**
     * Get all Doctors
     * 
     * @return List<Doctor>
     */
    public static List<Doctor> getDoctors() {
        return DoctorDatabase.getDB().getAllDoctors();
    }

    /**
     * Get all Administrators
     * 
     * @return List<Administrator>
     */
    public static List<Administrator> getAdministrators() {
        return AdministratorDatabase.getDB().getAllAdministrators();
    }

    /**
     * Get all Pharmacists
     * 
     * @return List<Pharmacist>
     */
    public static List<Pharmacist> getPharmacists() {
        return PharmacistDatabase.getDB().getAllPharmacist();
    }

    /**
     * Remove doctor
     * 
     * @param doctorID
     * @throws ModelNotFoundException
     */
    private static void removeDoctor(String doctorID) throws ModelNotFoundException {
        DoctorDatabase.getDB().remove(doctorID);
    }

    /**
     * Remove pharmacist
     * 
     * @param pharmacistID
     * @throws ModelNotFoundException
     */
    private static void removePharmacist(String pharmacistID) throws ModelNotFoundException {
        PharmacistDatabase.getDB().remove(pharmacistID);
    }

    /**
     * Remove administrator
     * 
     * @param administratorID
     * @throws ModelNotFoundException
     */
    private static void removeAdministrator(String administratorID) throws ModelNotFoundException {
        AdministratorDatabase.getDB().remove(administratorID);
    }

    /**
     * Remove patient
     * 
     * @param patientID
     * @throws ModelNotFoundException
     */
    private static void removePatient(String patientID) throws ModelNotFoundException {
        PatientDatabase.getDB().remove(patientID);
    }

    /**
     * Remove user by ID
     * 
     * @param staffID
     * @param userType
     * @throws ModelNotFoundException
     * @throws UserCannotBeFoundException
     */
    public static void removeUserByID(String staffID, UserType userType)
            throws ModelNotFoundException, UserCannotBeFoundException {
        if (findUserById(staffID, userType) == null) {
            throw new UserCannotBeFoundException(staffID, userType);
        }
        switch (userType) {
            case DOCTOR -> removeDoctor(staffID);
            case PATIENT -> removePatient(staffID);
            case PHARMACIST -> removePharmacist(staffID);
            case ADMINISTRATOR -> removeAdministrator(staffID);
        }
    }

}
