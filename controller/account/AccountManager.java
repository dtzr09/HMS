package controller.account;

import java.util.List;

import controller.authentication.PasswordManager;
import controller.user.UserManager;
import model.user.User;
import model.user.enums.Gender;
import model.user.enums.UserType;
import utils.exceptions.ModelAlreadyExistsException;
import utils.exceptions.ModelNotFoundException;
import utils.exceptions.PasswordDoesNotFulfilCriteriaException;
import utils.exceptions.PasswordIncorrectException;
import utils.exceptions.UserAlreadyExistsException;
import utils.exceptions.UserCannotBeFoundException;
import utils.iocontrol.CSVReader;

public class AccountManager {
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

    public static User login(UserType userType, String email, String password)
            throws PasswordIncorrectException, ModelNotFoundException {
        User user = UserManager.findUser(email, userType);
        if (PasswordManager.checkPassword(user, password)) {
            return user;
        } else {
            throw new PasswordIncorrectException();
        }
    }

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

    public static void changePassword(UserType userType, String email, String oldPassword, String newPassword)
            throws PasswordIncorrectException, ModelNotFoundException, PasswordDoesNotFulfilCriteriaException {
        User user = UserManager.findUser(email, userType);
        PasswordManager.changePassword(user, oldPassword, newPassword);
        UserManager.updateUser(user);
    }

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

    public void logout() {
        // logout
    }

    public void updateProfile() {
        // updateProfile
    }

    public void loadPatient() {
        // loadPatient
    }

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

    public void loadMedicine() {
        // loadMedicine
    }

}
