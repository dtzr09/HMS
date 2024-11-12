package controller.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import controller.authentication.PasswordManager;
import controller.user.UserManager;
import database.user.AdministratorDatabase;
import database.user.DoctorDatabase;
import database.user.PharmacistDatabase;
import model.user.Patient;
import model.user.PersonalInfo;
import model.user.User;
import model.user.enums.BloodType;
import model.user.enums.Gender;
import model.user.enums.UserType;
import utils.exceptions.ModelAlreadyExistsException;
import utils.exceptions.ModelNotFoundException;
import utils.exceptions.PasswordDoesNotFulfilCriteriaException;
import utils.exceptions.PasswordIncorrectException;
import utils.exceptions.UserAlreadyExistsException;
import utils.exceptions.UserCannotBeFoundException;
import utils.iocontrol.CSVReader;

/**
 * The AccountManager class provides utility methods for managing user accounts.
 */
public class AccountManager {

    /**
     * Checks if a user exists based on the provided email and user type.
     * 
     * This method queries the UserManager to determine if a user with the specified
     * email and user type is present. If the user is found, it returns true;
     * otherwise, it returns false. If a ModelNotFoundException is caught, it 
     * indicates that the user was not found.
     * 
     * @param email    The email address of the user to check.
     * @param userType The type of user (e.g., ADMINISTRATOR, etc.).
     * @return {@code true} if the user exists, {@code false} otherwise.
     */    
    public static boolean userExist(String email, UserType userType) {
        try {
            User user = UserManager.findUser(email, userType);
            if (user != null) {
                return true;
            }
            return false;
        } catch (ModelNotFoundException e) {
            return false;
        }

    }

    /**
     * Attempts to log in a user by verifying the provided email and password.
     * 
     * This method retrieves a user based on the specified email and user type. If the user
     * exists and the provided password matches the stored password, the user is returned.
     * Otherwise, an exception is thrown to indicate the failure reason.
     * 
     * @param userType The type of the user (e.g., ADMINISTRATOR, etc.).
     * @param email    The email address of the user attempting to log in.
     * @param password The password provided by the user for authentication.
     * @return The authenticated {@link User} object if login is successful.
     * @throws PasswordIncorrectException If the provided password does not match the stored password.
     * @throws ModelNotFoundException     If a user with the specified email and user type cannot be found.
     */
    public static User login(UserType userType, String email, String password)
            throws PasswordIncorrectException, ModelNotFoundException {
        User user = UserManager.findUser(email, userType);
        if (PasswordManager.checkPassword(user, password)) {
            return user;
        } else {
            throw new PasswordIncorrectException();
        }
    }

    /**
     * Registers a new user with the specified details.
     * 
     * This method creates a new user using the provided email, name, gender, age, and user type.
     * If the user is successfully created, it is returned. If a user with the specified email 
     * and user type already exists, an exception is thrown. A default password is assigned 
     * during user creation.
     * 
     * @param email    The email address of the user to register.
     * @param name     The name of the user.
     * @param gender   The gender of the user.
     * @param age      The age of the user.
     * @param userType The type of the user (e.g., ADMINISTRATOR, etc.).
     * @return The newly created {@link User} object if registration is successful.
     * @throws ModelNotFoundException         If the user model could not be created or found.
     * @throws UserAlreadyExistsException     If a user with the specified email already exists.
     */
    public static User register(String email, String name, Gender gender, int age, UserType userType)
            throws ModelNotFoundException, UserAlreadyExistsException {
        try {
            User user = UserManager.createUser(email, name, gender, age, userType, "password");
            return user;
        } catch (ModelAlreadyExistsException e) {
            System.out.println("Error registering user, user already exist");
        }
        return null;
    }

    /**
     * Changes the password of a user.
     * 
     * This method locates a user based on the specified email and user type, verifies
     * the old password, and updates it to a new password if the verification is successful.
     * If any validation or user-finding issues occur, appropriate exceptions are thrown.
     * 
     * @param userType    The type of the user (e.g., ADMINISTRATOR, etc.).
     * @param email       The email address of the user whose password is to be changed.
     * @param oldPassword The current password of the user for verification.
     * @param newPassword The new password to be set for the user.
     * @throws PasswordIncorrectException               If the old password provided does not match the stored password.
     * @throws ModelNotFoundException                   If a user with the specified email and user type cannot be found.
     * @throws PasswordDoesNotFulfilCriteriaException   If the new password does not meet the required criteria.
     */
    public static void changePassword(UserType userType, String email, String oldPassword, String newPassword)
            throws PasswordIncorrectException, ModelNotFoundException, PasswordDoesNotFulfilCriteriaException {
        User user = UserManager.findUser(email, userType);
        PasswordManager.changePassword(user, oldPassword, newPassword);
        UserManager.updateUser(user);
    }

    /**
     * Removes a user identified by the specified staff ID and user type.
     * 
     * This method attempts to remove a user based on the given staff ID and user type.
     * If the user cannot be found or a model-related issue arises during removal, 
     * the appropriate exceptions are thrown to indicate the specific error.
     * 
     * @param staffID  The ID of the staff to be removed.
     * @param userType The type of the user (e.g., ADMINISTRATOR, etc.).
     * @throws ModelNotFoundException       If the user model is not found.
     * @throws UserCannotBeFoundException   If the user with the specified staff ID and user type cannot be located.
     */
    public static void removeUser(String staffID, UserType userType)
            throws ModelNotFoundException, UserCannotBeFoundException {
        try {
            UserManager.removeUserByID(staffID, userType);
        } catch (UserCannotBeFoundException e) {
            throw new UserCannotBeFoundException(staffID, userType);
        } catch (ModelNotFoundException e) {
            throw new ModelNotFoundException("User not found");
        }
    }

    /**
     * Loads patient data from a CSV file and initializes patient objects.
     * 
     * This method reads patient data from a specified CSV file, creates a new {@link Patient}
     * object for each entry, and populates the user management system with these patients.
     * Each patient is assigned a unique ID, and relevant fields such as name, gender, blood
     * type, and email are extracted from the data. If any errors occur during data loading 
     * or processing, an error message is printed to the console.
     */
    public static void loadPatient() {
        List<List<String>> patients = CSVReader.read("./resources/Patient_List.csv", true);
        for (List<String> patient : patients) {
            try {
                String patientID = UUID.randomUUID().toString();
                String patientName = patient.get(1);
                String patientGender = patient.get(2);
                String patientBloodType = patient.get(3);
                String patientEmail = patient.get(4);
                String patientAge = patient.get(5);

                Gender gender = null;
                switch (patientGender) {
                    case "Male" -> gender = Gender.MALE;
                    case "Female" -> gender = Gender.FEMALE;
                }

                Date dateOfRegistration = new Date();
                BloodType bloodType = BloodType.fromString(patientBloodType);

                PersonalInfo personalInfo = new PersonalInfo(patientName, gender, Integer.parseInt(patientAge),
                        null,
                        patientEmail, null, dateOfRegistration);
                Patient newPatient = new Patient(patientID, "password", personalInfo, new ArrayList<>(), bloodType,
                        new ArrayList<>(),
                        null);

                UserManager.loadPatient(newPatient);
            } catch (Exception e) {
                System.out.println("Something went wrong with loading patients data");
            }
        }
    }

    /**
     * Loads hospital staff data from a CSV file and initializes staff objects.
     * 
     * This method reads data from a specified CSV file containing hospital staff information.
     * It creates new staff users based on their name, email, user type (such as Doctor, Pharmacist, 
     * or Administrator), gender, and age. The data is parsed from the CSV and then used to create 
     * users through the {@link UserManager}. If an error occurs due to a missing model or an existing 
     * user conflict, the exception details are printed.
     */
    public static void loadHospitalStaffs() {
        List<List<String>> hospitalStaffs = CSVReader.read("./resources/Staff_List.csv", true);
        for (List<String> hospitalStaff : hospitalStaffs) {
            try {
                String staffName = hospitalStaff.get(1);
                String staffEmail = hospitalStaff.get(2);
                String staffType = hospitalStaff.get(3);
                String staffGender = hospitalStaff.get(4);
                int age = Integer.valueOf(hospitalStaff.get(5));

                UserType userType = null;
                Gender gender = null;

                switch (staffType) {
                    case "Doctor" -> userType = UserType.DOCTOR;
                    case "Pharmacist" -> userType = UserType.PHARMACIST;
                    case "Administrator" -> userType = UserType.ADMINISTRATOR;
                }

                switch (staffGender) {
                    case "Male" -> gender = Gender.MALE;
                    case "Female" -> gender = Gender.FEMALE;
                }

                UserManager.createUser(staffEmail, staffName, gender, age, userType, "password");
            } catch (ModelNotFoundException | ModelAlreadyExistsException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Checks if the hospital staff repositories are empty.
     * 
     * This method determines whether any of the hospital staff databases (Administrator, 
     * Doctor, or Pharmacist) are empty. It returns {@code true} if any of the repositories 
     * contain no entries, indicating that at least one staff type is not present.
     * 
     * @return {@code true} if any of the hospital staff repositories are empty; 
     *         {@code false} otherwise.
     */
    public static boolean isHospitalStaffsRepositoryEmpty() {
        return AdministratorDatabase.getDB().isEmpty() || DoctorDatabase.getDB().isEmpty()
                || PharmacistDatabase.getDB().isEmpty();
    }

}
