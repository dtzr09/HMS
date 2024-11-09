package controller.account;

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
import utils.utils.FormatDateTime;

public class AccountManager {

    /**
     * Check if user exists
     * 
     * @param email
     * @param userType
     * @return
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
     * Login
     * 
     * @param userType
     * @param email
     * @param password
     * @return
     * @throws PasswordIncorrectException
     * @throws ModelNotFoundException
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
     * Register
     * 
     * @param email
     * @param name
     */
    public static User register(String email, String name, Gender gender, int age, UserType userType)
            throws ModelNotFoundException, UserAlreadyExistsException {
        try {
            User user = UserManager.createUser(email, name, gender, age, userType, "password");
            return user;
        } catch (Exception e) {
            System.out.println("Error registering user");
        }
        return null;
    }

    /**
     * Change password
     * 
     * @param userType
     * @param email
     * @param oldPassword
     * @param newPassword
     * @throws PasswordIncorrectException
     * @throws ModelNotFoundException
     * @throws PasswordDoesNotFulfilCriteriaException
     */
    public static void changePassword(UserType userType, String email, String oldPassword, String newPassword)
            throws PasswordIncorrectException, ModelNotFoundException, PasswordDoesNotFulfilCriteriaException {
        User user = UserManager.findUser(email, userType);
        PasswordManager.changePassword(user, oldPassword, newPassword);
        UserManager.updateUser(user);
    }

    /**
     * Remove user
     * 
     * @param staffID
     * @param userType
     * @throws ModelNotFoundException
     * @throws UserCannotBeFoundException
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
     * Load patient
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
                Patient newPatient = new Patient(patientID, "password", personalInfo, null, bloodType, null, null,
                        null);

                UserManager.loadPatient(newPatient);
            } catch (Exception e) {
                System.out.println("Something went wrong with loading patients data");
            }
        }
    }

    /**
     * Load hospital staffs
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

    public static boolean isHospitalStaffsRepositoryEmpty() {
        return AdministratorDatabase.getDB().isEmpty() || DoctorDatabase.getDB().isEmpty()
                || PharmacistDatabase.getDB().isEmpty();
    }

}
