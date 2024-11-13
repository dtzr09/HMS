package controller.user;

import controller.account.AccountManager;
import database.user.AdministratorDatabase;
import model.user.enums.Gender;
import model.user.enums.UserType;
import utils.exceptions.ModelNotFoundException;
import utils.exceptions.UserAlreadyExistsException;
import utils.exceptions.UserCannotBeFoundException;
/**
 * The AdministratorManager class provides utility methods for handling the functionalities of an Administrator.
 */

public class AdministratorManager {

    /**
     * Checks if the Administrator database is empty.
     * 
     * This method queries the Administrator database to check if it contains any records. 
     * It returns true if the database is empty, and false if it contains any records.
     * 
     * @return true if the Administrator database is empty; false if it contains records.
     */
    public static boolean isRepositoryEmpty() {
        return AdministratorDatabase.getDB().isEmpty();
    }

    /**
     * Registers a new hospital staff member by adding them to the system.
     * 
     * This method registers a new hospital staff member by calling the `AccountManager.register` method.
     * It requires the staff member's email, name, gender, age, and user type as inputs.
     * If the staff member already exists, a `UserAlreadyExistsException` is thrown. 
     * If there is an issue with the provided user type or model, a `ModelNotFoundException` will be thrown.
     * 
     * @param email the email address of the staff member.
     * @param name the name of the staff member.
     * @param gender the gender of the staff member.
     * @param age the age of the staff member.
     * @param userType the user type (e.g., ADMINISTRATOR, DOCTOR) of the staff member.
     * 
     * @throws ModelNotFoundException if the model or user type cannot be found or is invalid.
     * @throws UserAlreadyExistsException if a user with the same email already exists in the system.
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
     * Removes an existing hospital staff member from the system.
     * 
     * This method attempts to remove a staff member based on their email and user type by calling 
     * the `AccountManager.removeUser()` method. If the user is not found in the system, 
     * it throws a `ModelNotFoundException`. If the specific user cannot be found or removed, 
     * a `UserCannotBeFoundException` is thrown.
     * 
     * @param email the email address of the staff member to be removed.
     * @param userType the user type (e.g., ADMINISTRATOR, DOCTOR) of the staff member to be removed.
     * 
     * @throws ModelNotFoundException if the staff member's model cannot be found in the system.
     * @throws UserCannotBeFoundException if the specific user cannot be found based on the provided email and user type.
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
