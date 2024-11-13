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
     * Find doctor by email.
     * 
     * This method retrieves the doctor by their email address from the `DoctorDatabase`.
     * 
     * @param email the email address of the doctor.
     * @return the doctor with the specified email.
     * @throws ModelNotFoundException if no doctor is found with the specified email.
     */
    private static User findDoctorByEmail(String email) throws ModelNotFoundException {
        return DoctorDatabase.getDB().getByEmail(email);
    }

    /**
     * Find patient by email.
     * 
     * This method retrieves the patient by their email address from the `PatientDatabase`.
     * 
     * @param email the email address of the patient.
     * @return the patient with the specified email.
     * @throws ModelNotFoundException if no patient is found with the specified email.
     */
    private static User findPatientByEmail(String email) throws ModelNotFoundException {
        return PatientDatabase.getDB().getByEmail(email);
    }

    /**
     * Find pharmacist by email.
     * 
     * This method retrieves the pharmacist by their email address from the `PharmacistDatabase`.
     * 
     * @param email the email address of the pharmacist.
     * @return the pharmacist with the specified email.
     * @throws ModelNotFoundException if no pharmacist is found with the specified email.
     */
    private static User findPhamarcistByEmail(String email) throws ModelNotFoundException {
        return PharmacistDatabase.getDB().getByEmail(email);
    }

    /**
     * Find administrator by email.
     * 
     * This method retrieves the administrator by their email address from the `AdministratorDatabase`.
     * 
     * @param email the email address of the administrator.
     * @return the administrator with the specified email.
     * @throws ModelNotFoundException if no administrator is found with the specified email.
     */
    private static User findAdministratorByEmail(String email) throws ModelNotFoundException {
        return AdministratorDatabase.getDB().getByEmail(email);
    }

    /**
     * Find user by email and user type.
     * 
     * This method retrieves the user with the specified email and user type (doctor, patient, pharmacist, administrator).
     * 
     * @param email the email address of the user.
     * @param userType the type of user (doctor, patient, pharmacist, or administrator).
     * @return the user with the specified email and user type.
     * @throws ModelNotFoundException if no user is found with the specified email and user type.
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
     * Find doctor by ID.
     * 
     * This method retrieves the doctor by their ID from the `DoctorDatabase`.
     * 
     * @param doctorID the ID of the doctor.
     * @return the doctor with the specified ID.
     * @throws ModelNotFoundException if no doctor is found with the specified ID.
     */
    private static User findDoctorById(String doctorID) throws ModelNotFoundException {
        return DoctorDatabase.getDB().getByID(doctorID);
    }

    /**
     * Find patient by ID.
     * 
     * This method retrieves the patient by their ID from the `PatientDatabase`.
     * 
     * @param patientID the ID of the patient.
     * @return the patient with the specified ID.
     * @throws ModelNotFoundException if no patient is found with the specified ID.
     */
    private static User findPatientById(String patientID) throws ModelNotFoundException {
        return PatientDatabase.getDB().getByID(patientID);
    }

    /**
     * Find pharmacist by ID.
     * 
     * This method retrieves the pharmacist by their ID from the `PharmacistDatabase`.
     * 
     * @param pharmacistID the ID of the pharmacist.
     * @return the pharmacist with the specified ID.
     * @throws ModelNotFoundException if no pharmacist is found with the specified ID.
     */
    private static User findPharmacistById(String pharmacistID) throws ModelNotFoundException {
        return PharmacistDatabase.getDB().getByID(pharmacistID);
    }

    /**
     * Find administrator by ID.
     * 
     * This method retrieves the administrator by their ID from the `AdministratorDatabase`.
     * 
     * @param administratorID the ID of the administrator.
     * @return the administrator with the specified ID.
     * @throws ModelNotFoundException if no administrator is found with the specified ID.
     */
    private static User findAdministratorById(String administratorID) throws ModelNotFoundException {
        return AdministratorDatabase.getDB().getByID(administratorID);
    }

    /**
     * Find user by ID and user type.
     * 
     * This method retrieves the user by their ID and user type (doctor, patient, pharmacist, administrator).
     * 
     * @param userID the ID of the user.
     * @param userType the type of user (doctor, patient, pharmacist, or administrator).
     * @return the user with the specified ID and user type.
     * @throws ModelNotFoundException if no user is found with the specified ID and user type.
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
     * Update pharmacist details.
     * 
     * This method updates the pharmacist's details in the `PharmacistDatabase`.
     * 
     * @param pharmacist the pharmacist to be updated.
     * @throws ModelNotFoundException if the pharmacist is not found in the database.
     */
    private static void updatePharmacist(Pharmacist pharmacist) throws ModelNotFoundException {
        PharmacistDatabase.getDB().update(pharmacist);
    }

    /**
     * Update doctor details.
     * 
     * This method updates the doctor's details in the `DoctorDatabase`.
     * 
     * @param doctor the doctor to be updated.
     * @throws ModelNotFoundException if the doctor is not found in the database.
     */
    private static void updateDoctor(Doctor doctor) throws ModelNotFoundException {
        DoctorDatabase.getDB().update(doctor);
    }

    /**
     * Update patient details.
     * 
     * This method updates the patient's details in the `PatientDatabase`.
     * 
     * @param patient the patient to be updated.
     * @throws ModelNotFoundException if the patient is not found in the database.
     */
    private static void updatePatient(Patient patient) throws ModelNotFoundException {
        PatientDatabase.getDB().update(patient);
    }

    /**
     * Update administrator details.
     * 
     * This method updates the administrator's details in the `AdministratorDatabase`.
     * 
     * @param administrator the administrator to be updated.
     * @throws ModelNotFoundException if the administrator is not found in the database.
     */
    private static void updateAdministrator(Administrator administrator) throws ModelNotFoundException {
        AdministratorDatabase.getDB().update(administrator);
    }

    /**
     * Update user details based on user type.
     * 
     * This method updates the details of the user based on their type (doctor, patient, pharmacist, or administrator).
     * 
     * @param user the user whose details need to be updated.
     * @throws ModelNotFoundException if the user is not found in their respective database.
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
     * Update user profile details.
     * 
     * This method updates the user's profile details based on a map of updated values.
     * 
     * @param user the user whose profile details need to be updated.
     * @param userType the type of the user (doctor, patient, pharmacist, or administrator).
     * @param updatedValues a map of profile fields to be updated, with field names as keys and new values as map entries.
     * @throws ModelNotFoundException if the user is not found in their respective database.
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
     * Create a doctor.
     * 
     * This method adds a new doctor to the `DoctorDatabase`.
     * 
     * @param doctor the doctor to be created.
     * @throws ModelAlreadyExistsException if a doctor with the same ID already exists.
     */
    private static void createDoctor(Doctor doctor) throws ModelAlreadyExistsException {
        DoctorDatabase.getDB().add(doctor);
    }

    /**
     * Load a doctor.
     * 
     * This method is used to load a doctor by calling the `createDoctor` method.
     * 
     * @param doctor the doctor to be loaded.
     * @throws ModelAlreadyExistsException if a doctor with the same ID already exists.
     */
    public static void loadDoctor(Doctor doctor) throws ModelAlreadyExistsException {
        createDoctor(doctor);
    }

    /**
     * Load a patient.
     * 
     * This method is used to load a patient by calling the `createPatient` method.
     * 
     * @param patient the patient to be loaded.
     * @throws ModelAlreadyExistsException if a patient with the same ID already exists.
     */
    public static void loadPatient(Patient patient) throws ModelAlreadyExistsException {
        createPatient(patient);
    }

    /**
     * Create a patient.
     * 
     * This method adds a new patient to the `PatientDatabase`.
     * 
     * @param patient the patient to be created.
     * @throws ModelAlreadyExistsException if a patient with the same ID already exists.
     */
    private static void createPatient(Patient patient) throws ModelAlreadyExistsException {
        PatientDatabase.getDB().add(patient);
    }

    /**
     * Create a pharmacist.
     * 
     * This method adds a new pharmacist to the `PharmacistDatabase`.
     * 
     * @param pharmacist the pharmacist to be created.
     * @throws ModelAlreadyExistsException if a pharmacist with the same ID already exists.
     */
    private static void createPharmacist(Pharmacist pharmacist) throws ModelAlreadyExistsException {
        PharmacistDatabase.getDB().add(pharmacist);
    }

    /**
     * Create an administrator.
     * 
     * This method adds a new administrator to the `AdministratorDatabase`.
     * 
     * @param administrator the administrator to be created.
     * @throws ModelAlreadyExistsException if an administrator with the same ID already exists.
     */
    private static void createAdministrator(Administrator administrator) throws ModelAlreadyExistsException {
        AdministratorDatabase.getDB().add(administrator);
    }

    /**
     * Create a user.
     * 
     * This method creates a user (doctor, patient, pharmacist, or administrator) based on the provided details.
     * It assigns a unique ID to the user and creates the user in the respective database.
     * 
     * @param email the email of the user.
     * @param name the name of the user.
     * @param gender the gender of the user.
     * @param age the age of the user.
     * @param userType the type of user (doctor, patient, pharmacist, or administrator).
     * @param password the password for the user.
     * @return the newly created user.
     * @throws ModelNotFoundException if the user cannot be created due to invalid or missing data.
     * @throws ModelAlreadyExistsException if a user with the same ID already exists.
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
     * Create a user with a specific UUID.
     * 
     * This method allows for creating a user (doctor, patient, pharmacist, or administrator) with a predefined UUID, instead of generating a new one. 
     * The user is created in the appropriate database based on the user type (doctor, patient, pharmacist, or administrator).
     * 
     * @param userID the unique user ID.
     * @param email the email address of the user.
     * @param name the name of the user.
     * @param gender the gender of the user.
     * @param age the age of the user.
     * @param userType the type of the user (doctor, patient, pharmacist, or administrator).
     * @param password the password for the user.
     * @return the newly created user with the provided UUID.
     * @throws ModelNotFoundException if the user cannot be created due to invalid or missing data.
     * @throws ModelAlreadyExistsException if a user with the same ID already exists.
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
     * @return List of all doctors in the database
     */
    public static List<Doctor> getDoctors() {
        return DoctorDatabase.getDB().getAllDoctors();
    }

    /**
     * Get all Administrators
     * 
     * @return List of all administrators in the database
     */
    public static List<Administrator> getAdministrators() {
        return AdministratorDatabase.getDB().getAllAdministrators();
    }

    /**
     * Get all Pharmacists
     * 
     * @return List of all pharmacists in the database
     */
    public static List<Pharmacist> getPharmacists() {
        return PharmacistDatabase.getDB().getAllPharmacist();
    }

    /**
     * Remove a doctor from the database
     * 
     * @param doctorID the ID of the doctor to be removed
     * @throws ModelNotFoundException if the doctor cannot be found in the database
     */
    private static void removeDoctor(String doctorID) throws ModelNotFoundException {
        DoctorDatabase.getDB().remove(doctorID);
    }

    /**
     * Remove a pharmacist from the database
     * 
     * @param pharmacistID the ID of the pharmacist to be removed
     * @throws ModelNotFoundException if the pharmacist cannot be found in the database
     */
    private static void removePharmacist(String pharmacistID) throws ModelNotFoundException {
        PharmacistDatabase.getDB().remove(pharmacistID);
    }

    /**
     * Remove an administrator from the database
     * 
     * @param administratorID the ID of the administrator to be removed
     * @throws ModelNotFoundException if the administrator cannot be found in the database
     */
    private static void removeAdministrator(String administratorID) throws ModelNotFoundException {
        AdministratorDatabase.getDB().remove(administratorID);
    }

    /**
     * Remove a patient from the database
     * 
     * @param patientID the ID of the patient to be removed
     * @throws ModelNotFoundException if the patient cannot be found in the database
     */
    private static void removePatient(String patientID) throws ModelNotFoundException {
        PatientDatabase.getDB().remove(patientID);
    }

    /**
     * Remove a user by their ID and user type.
     * 
     * @param staffID the ID of the staff member to be removed
     * @param userType the type of the user (DOCTOR, PATIENT, PHARMACIST, ADMINISTRATOR)
     * @throws ModelNotFoundException if the user could not be found in the database
     * @throws UserCannotBeFoundException if the user with the given ID and type does not exist
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
