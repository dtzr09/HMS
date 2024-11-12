package controller.user;

import controller.account.AccountManager;
import database.user.AdministratorDatabase;
import model.user.enums.Gender;
import model.user.enums.UserType;
import utils.exceptions.ModelNotFoundException;
import utils.exceptions.UserAlreadyExistsException;
import utils.exceptions.UserCannotBeFoundException;

public class AdministratorManager {

    /**
     * Check if the administrator database is empty
     * 
     * @return true if the administrator database is empty, false otherwise
     */
    public static boolean isRepositoryEmpty() {
        return AdministratorDatabase.getDB().isEmpty();
    }

    /**
     * Add a new hospital staff
     * 
     * @param email
     * @param name
     * @param gender
     * @param age
     * @param userType
     * @throws ModelNotFoundException
     * @throws UserAlreadyExistsException
     */
    public static void addNewHospitalStaff(String email, String name, Gender gender, int age, UserType userType)
            throws ModelNotFoundException, UserAlreadyExistsException {
        try {
            AccountManager.register(email, name, gender, age, userType);
        } catch (ModelNotFoundException e) {
            throw new ModelNotFoundException("User already exists");
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException(userType, email);
        }
    }

    /**
     * Remove a hospital staff
     * 
     * @param email
     * @param userType
     * @throws ModelNotFoundException
     * @throws UserCannotBeFoundException
     */
    public static void removeNewHospitalStaff(String email, UserType userType)
            throws ModelNotFoundException, UserCannotBeFoundException {
        try {
            AccountManager.removeUser(email, userType);
        } catch (ModelNotFoundException e) {
            throw new ModelNotFoundException("User not found");
        } catch (UserCannotBeFoundException e) {
            throw new UserCannotBeFoundException(email, userType);
        }
    }

}
